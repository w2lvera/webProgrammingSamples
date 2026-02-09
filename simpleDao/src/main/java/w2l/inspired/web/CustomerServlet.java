package w2l.inspired.web;
import jakarta.servlet.annotation.WebServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import w2l.inspired.dao.CustomerDao;
import w2l.inspired.dao.CustomerSimpleDao;
import w2l.inspired.logical.CustomerScoreCalc;
import w2l.inspired.logical.CustomersChecker;
import w2l.inspired.logical.DailyLogProcessor;
import w2l.inspired.model.CompletionStatus;
import w2l.inspired.model.Customer;
import w2l.inspired.model.DailyLog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import static java.lang.System.out;
@WebServlet("/customersPrefix")
public class CustomerServlet extends HttpServlet {
    private CustomerSimpleDao customerSimpleDao;
    private CustomersChecker checker;
    private DailyLogProcessor logProcessor;
    private CustomerScoreCalc calc;
    public static Logger LOGGER = LogManager.getLogger(CustomersChecker.class);
    @Override
    public void init()  {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("config.xml"));
        checker = (CustomersChecker)factory.getBean("customerChecker");
        customerSimpleDao =(CustomerSimpleDao) factory.getBean("customerDao");
        calc = (CustomerScoreCalc)factory.getBean(("calc"));
        logProcessor = checker.getLogProcessor();
        // checker.run();

    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html");
//        PrintWriter writer = null;
//        try {
//            writer = response.getWriter();
//            writer.println("<h2> hello servlet</h2>");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } finally {
//            writer.close();
//        }

        try(PrintWriter out = response.getWriter()){
            List<Customer> customers = customerSimpleDao.getCustomers();
            out.println("<form method=\"post\" action = \"http://localhost:8080/simpleDao/customersPrefix\">");
            out.println("<h1>результат за сегодня "+ LocalDate.now()+"</h1\n<ul>");
            for(Customer c:customers){
                out.println("<li>" +c.getName()+"<input type =\"checkbox\" name =\"" +c.getId()+ "\"/></li>");
            }
            out.println("</ul>");
            out.println("<input type =\"submit\"/>");
            out.println("</form>");
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while((line = reader.readLine())!=null){
            stringBuilder.append(line);
        }
        String []requestParametrs = stringBuilder.toString().split("&");

        LinkedList<DailyLog> list =  new LinkedList<>();
        try {
            list.addAll(logProcessor.getLog());
        } catch (IOException e) {
            LOGGER.warn("Customers are not read!");
        }
        out.println("Баланс: " + calc.calculateScore(list));
        out.println("Заполняем результат за " + LocalDate.now());
        List<Customer> customers= customerSimpleDao.getCustomers();
        for(Customer c:customers){
            CompletionStatus s=CompletionStatus.FAILED;
            for(String pair:requestParametrs) {
                String[] split = pair.split("=");
                if (Integer.parseInt(split[0]) == c.getId())
                    s = CompletionStatus.DONE;
            }
            DailyLog newLogEntry = new DailyLog(c,s);
            list.addLast(newLogEntry);
        }
        int balance = calc.calculateScore(list);
        try {
            logProcessor.reWriteLog(list);
        } catch (IOException e) {
            LOGGER.warn("Motivation Events are not stored!");
        }
        response.setContentType("text/html");
        try(PrintWriter out = response.getWriter() ){
            out.println("<h1> Получен результат за "+ LocalDate.now()+"</h1>");
            out.println("<h2> Customers with prefix "+ calc.getPrefix()+" = "+balance+"</h2>");
        }
    }
}
