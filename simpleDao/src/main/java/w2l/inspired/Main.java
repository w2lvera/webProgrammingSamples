package w2l.inspired;

import w2l.inspired.dao.CustomerDao;
import w2l.inspired.dao.CustomerSimpleDao;
import w2l.inspired.logical.CustomerScoreCalc;
import w2l.inspired.logical.CustomersChecker;
import w2l.inspired.logical.DailyLogFileProcessor;
import w2l.inspired.logical.DailyLogProcessor;
import w2l.inspired.model.Customer;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //init spring beans
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("config.xml"));

        //MotivationEventsChecker checker = factory.getBean("motivationChecker", MotivationEventsChecker.class);
        CustomersChecker checker = (CustomersChecker)factory.getBean("customerChecker");
//        Customer customer1 = new Customer(1, "Ivanov");
//        Customer customer2 = new Customer(2, "Petrov");
//        Customer customer3 = new Customer(3, "Sidorov");
//        Customer customer4 = new Customer(4, "Pushkin");
//
     // List<Customer> list = List.of(customer1,customer2,customer3,customer4);
       CustomerDao customerDao = new CustomerSimpleDao();
//        CustomerScoreCalc calc = new CustomerScoreCalc("P",0);
//        DailyLogProcessor processor = new DailyLogFileProcessor(customerDao);
////        то есть мы в коде жестко прописываем какие экземпляры создавать и куда их передавать как параметры
//        CustomersChecker checker = new CustomersChecker(customerDao,processorCustomerDao customerDao = new CustomerSimpleDao();
 //      CustomerScoreCalc calc = new CustomerScoreCalc("P",0);
   //    DailyLogProcessor processor = new DailyLogFileProcessor(customerDao);
//       то есть мы в коде жестко прописываем какие экземпляры создавать и куда их передавать как параметры
     //   CustomersChecker checker = new CustomersChecker(customerDao,processor,calc);
        checker.run();
    }
}
//       CustomerDao customerDao = new CustomerSimpleDao();
//        CustomerScoreCalc calc = new CustomerScoreCalc("P",0);
//        DailyLogProcessor processor = new DailyLogFileProcessor(customerDao);
////        то есть мы в коде жестко прописываем какие экземпляры создавать и куда их передавать как параметры
//        CustomersChecker checker = new CustomersChecker(customerDao,processor,calc);