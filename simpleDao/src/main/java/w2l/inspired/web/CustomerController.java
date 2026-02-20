package w2l.inspired.web;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import w2l.inspired.dao.CustomerSimpleDao;
import w2l.inspired.logical.CustomerScoreCalc;
import w2l.inspired.logical.CustomersChecker;
import w2l.inspired.logical.DailyLogProcessor;



@Controller
public class CustomerController {
    @Autowired
    private CustomerSimpleDao customerSimpleDao;
//    @Autowired
//    private CustomersChecker checker;
//    @Autowired
//    private DailyLogProcessor logProcessor;
//    @Autowired
//    private CustomerScoreCalc calc;
//    public static Logger LOGGER = LogManager.getLogger(CustomersChecker.class);
    @ResponseBody
    public String getGreeting() {
        return "Hello world.";
    }
//    @RequestMapping (path = "/today", method = RequestMethod.GET)
//    public void today() {
//        if(this.customerSimpleDao == null || this.calc == null)
//            LOGGER.error("Context is not loaded");
//    }

    //   public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
 //       response.setContentType("text/html");
//        PrintWriter writer = null;
//        try {
//            writer = response.getWriter();
//            writer.println("<h2> hello servlet</h2>");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } finally {
//            writer.close();
//        }

//        try(PrintWriter out = response.getWriter()){
//            List<Customer> customers = customerSimpleDao.getCustomers();
//            out.println("<form method=\"post\" action = \"http://localhost:8080/simpleDao/customersPrefix\">");
//            out.println("<h1>результат за сегодня "+ LocalDate.now()+"</h1\n<ul>");
//            for(Customer c:customers){
//                out.println("<li>" +c.getName()+"<input type =\"checkbox\" name =\"" +c.getId()+ "\"/></li>");
//            }
//            out.println("</ul>");
//            out.println("<input type =\"submit\"/>");
//            out.println("</form>");
//        }
//    }
//    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setContentType("text/html");
//        StringBuilder stringBuilder = new StringBuilder();
//        BufferedReader reader = request.getReader();
//        String line;
//        while((line = reader.readLine())!=null){
//            stringBuilder.append(line);
//        }
//        String []requestParametrs = stringBuilder.toString().split("&");
//
//        LinkedList<DailyLog> list =  new LinkedList<>();
//        try {
//            list.addAll(logProcessor.getLog());
//        } catch (IOException e) {
//            LOGGER.warn("Customers are not read!");
//        }
//        out.println("Баланс: " + calc.calculateScore(list));
//        out.println("Заполняем результат за " + LocalDate.now());
//        List<Customer> customers= customerSimpleDao.getCustomers();
//        for(Customer c:customers){
//            CompletionStatus s=CompletionStatus.FAILED;
//            for(String pair:requestParametrs) {
//                String[] split = pair.split("=");
//                if (Integer.parseInt(split[0]) == c.getId())
//                    s = CompletionStatus.DONE;
//            }
//            DailyLog newLogEntry = new DailyLog(c,s);
//            list.addLast(newLogEntry);
//        }
//        int balance = calc.calculateScore(list);
//        try {
//            logProcessor.reWriteLog(list);
//        } catch (IOException e) {
//            LOGGER.warn("Motivation Events are not stored!");
//        }
//        response.setContentType("text/html");
//        try(PrintWriter out = response.getWriter() ){
//            out.println("<h1> Получен результат за "+ LocalDate.now()+"</h1>");
//            out.println("<h2> Customers with prefix "+ calc.getPrefix()+" = "+balance+"</h2>");
//        }
//    }
}
