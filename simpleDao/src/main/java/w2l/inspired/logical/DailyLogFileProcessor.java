package w2l.inspired.logical;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import w2l.inspired.dao.CustomerDao;
import w2l.inspired.model.CompletionStatus;
import w2l.inspired.model.Customer;
import w2l.inspired.model.DailyLog;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
@Component
public class DailyLogFileProcessor implements DailyLogProcessor {

    static final String DATE_FORMAT = "dd.MM.yy";
    private static final String DEFAULT_FILE_NAME = "customerLog.csv";
    private final CustomerDao customerDao;
    private final String filePath;
    @Autowired
    public DailyLogFileProcessor(CustomerDao customerDao, @Value("${output.file.path}") String outPath) {
        this.customerDao = customerDao;
        this.filePath = outPath;
    }

    @Override
    public List<DailyLog> getLog() throws IOException {
        List<DailyLog> list = new LinkedList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        File dataDir = new File(filePath);
        File targetFile = getTargetFile(dataDir);
        try (InputStream inputStream = new FileInputStream(targetFile)){
            InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);
            for (String line; (line = reader.readLine()) != null;){
                DailyLog log = mapDailyLog(dateFormat, line);
                list.add(log);
            }
        } catch (ParseException e) {
            throw new IOException(e);
        }
        return list;
    }

    public DailyLog mapDailyLog(SimpleDateFormat dateFormat, String line) throws ParseException {
        String[] lineSplit = line.split(",");
        if(lineSplit.length!=3){
            throw new ParseException("Error in file",0);
        }
        Customer customer = customerDao.getCustomerById(Integer.parseInt(lineSplit[1].trim()));
        LocalDate date = Instant.ofEpochMilli(dateFormat.parse(lineSplit[0].trim()).getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        CompletionStatus status = CompletionStatus.valueOf(lineSplit[2].trim());
        return new DailyLog(date,customer,status);
    }

    @Override
    public void reWriteLog(List<DailyLog> log) throws IOException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        File dataDir = new File(filePath);
        File targetFile = getTargetFile(dataDir);
        try (FileWriter writer = new FileWriter(targetFile, true)) {
            for (DailyLog logEntry : log) {
                String dateStr = logEntry.getDate().format(dateTimeFormatter);
                writer.write(dateStr + "," + logEntry.getCustomer().getId() + "," + logEntry.getStatus().name()+"\n");
            }
        }
    }

    private static File getTargetFile(File dataDir) {
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }
        File targetFile = new File(dataDir, DEFAULT_FILE_NAME);
        return targetFile;
    }
}
