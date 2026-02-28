package w2l.inspired.web;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import w2l.inspired.dao.CustomerSimpleDao;
import w2l.inspired.logical.CustomerScoreCalc;
import w2l.inspired.logical.CustomersChecker;
import w2l.inspired.logical.DailyLogProcessor;
import w2l.inspired.model.CompletionStatus;
import w2l.inspired.model.Customer;
import w2l.inspired.model.DailyLog;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;


@Controller
public class CustomerController {
    @Autowired
    private CustomerSimpleDao customerSimpleDao;
    @Autowired
    private CustomersChecker checker;
    @Autowired
    private DailyLogProcessor logProcessor;
    @Autowired
    private CustomerScoreCalc calc;
    public static Logger LOGGER = LogManager.getLogger(CustomersChecker.class);
    @RequestMapping({"/"})
    public String index(){return "index";}
    @RequestMapping(path = "/today", method = RequestMethod.GET)
    public ModelAndView getCustomersForToday() {
        ModelAndView modelAndView = new ModelAndView("markCustomers1");
        modelAndView.addObject("customers", customerSimpleDao.getCustomers());
        modelAndView.addObject("serverTime",LocalDate.now());
        return modelAndView;
    }
    @RequestMapping(path = "/today", method = RequestMethod.POST)
    public ModelAndView returnResults(@RequestBody(required = false) String payload) throws IOException {


        ModelAndView mv = new ModelAndView("result");
        mv.addObject("serverTime", LocalDate.now());
        mv.addObject("inputString", payload);
        List<DailyLog> list = new LinkedList<>();// readDailyLogFile();

        int initialBalance = calc.calculateScore(list);
        mv.addObject("yesterday", initialBalance);


        String[] requestParams = (payload == null || payload.isEmpty()) ? new String[0]
                : payload.split("&"); //1=on&2=on  , off  doesn't exist
        List<Customer> customers = customerSimpleDao.getCustomers();
        for(Customer c:customers){
            CompletionStatus s=CompletionStatus.FAILED;
            for(String pair:requestParams) {
                String[] split = pair.split("=");
                if (Integer.parseInt(split[0]) == c.getId())
                    s = CompletionStatus.DONE;
            }
            DailyLog newLogEntry = new DailyLog(c,s);
            list.add(newLogEntry);

        }
        mv.addObject("logList", list);
//        for (Customer customer :customerSimpleDao.getCustomers()) {
//            boolean found = false;
//            for (String pair : requestParams) {
//                String[] split = pair.split("=");
//                if (customer.getId() == Integer.parseInt(split[0])) {
//                    found = true;
//                }
//            }
//            CompletionStatus status = found ? CompletionStatus.DONE : CompletionStatus.FAILED;
//            DailyLog log = new DailyLog(customer, status);
//            list.add(log);
//        }


        logProcessor.reWriteLog(list);
        int balance = calc.calculateScore(list);
        mv.addObject("now", balance);
        return mv;
    }
 //   String []requestParametrs = stringBuilder.toString().split("&");
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

//    @GetMapping("/hello")
//    public String handle(Model model) {
//        List<Customer> customers = customerSimpleDao.getCustomers();
//        model.addAttribute("message","результат за сегодня "+ LocalDate.now());
//        model.addAttribute("customers",customers.size());
//        int i=1;
//        for(Customer c:customers){
//               model.addAttribute("customer"+i++, c.getName());
//        }
//    return "index";
//}

//    @RequestMapping (path = "/today", method = RequestMethod.GET)
//    public void today() {
//        if (this.customerSimpleDao == null || this.calc == null)
//            LOGGER.error("Context is not loaded");
//
//
//        response.setContentType("text/html");
//        PrintWriter writer = null;
//        try {
//            writer = response.getWriter();
//            writer.println("<h2> hello servlet</h2>");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } finally {
//            writer.close();
//        }
//    }

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
//    @RequestMapping (path = "/customersPrefix", method = RequestMethod.POST)
//    public void doPost(){
//
//    }
//}
 //       public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
private List<DailyLog> readDailyLogFile() {
    List<DailyLog> list = new LinkedList<>();
    try {
        list.addAll(logProcessor.getLog());
    } catch (IOException e) {
        LOGGER.warn("Customers are not read!");
    }
    return list;
}

}
