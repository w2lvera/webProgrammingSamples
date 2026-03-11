package w2l.inspired;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import w2l.inspired.dao.CustomerDao;
import w2l.inspired.dao.CustomerSimpleDao;
import w2l.inspired.logical.CustomersChecker;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new  ClassPathXmlApplicationContext("context.xml");
       CustomersChecker customersChecker = context.getBean("customersChecker", CustomersChecker.class);
       customersChecker.run();
    }
}
