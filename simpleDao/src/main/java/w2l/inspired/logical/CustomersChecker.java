package w2l.inspired.logical;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import w2l.inspired.dao.CustomerDao;
import w2l.inspired.model.CompletionStatus;
import w2l.inspired.model.Customer;
import w2l.inspired.model.DailyLog;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CustomersChecker {

    public static Logger LOGGER = LogManager.getLogger(CustomersChecker.class);

    private final CustomerDao customerDao;
    private final DailyLogProcessor logProcessor;
    private final CustomerScoreCalc calc;

    public CustomersChecker(CustomerDao customerDao,
                                   DailyLogProcessor logProcessor,
                                   CustomerScoreCalc calc) {
        this.customerDao = customerDao;
        this.logProcessor = logProcessor;
        this.calc = calc;
    }

    public void run(){
        PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        Scanner in = new Scanner(System.in);
        List<Customer> customers = customerDao.getCustomers();
        LinkedList<DailyLog> list =  new LinkedList<>();
        try {
            list.addAll(logProcessor.getLog());
        } catch (IOException e) {
           LOGGER.warn("Motivation Events are not read!");
        }
        out.println("Баланс: " + calc.calculateScore(list));
        out.println("Заполняем результат за " + LocalDate.now());
        for(Customer customer: customers){
            out.println(customer.getName());
            String result = in.next();
            CompletionStatus s = (result.equals("y"))? CompletionStatus.DONE : CompletionStatus.FAILED;
            DailyLog newLogEntry = new DailyLog(customer,s);
            list.addLast(newLogEntry);
        }
        try {
            logProcessor.reWriteLog(list);
        } catch (IOException e) {
            LOGGER.warn("Motivation Events are not stored!");
        }
        int balance = calc.calculateScore(list);
        out.println("Баланс на конец дня: " + balance);

    }

}
