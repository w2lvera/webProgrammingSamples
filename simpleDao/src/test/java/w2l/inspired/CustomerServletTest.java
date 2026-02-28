package w2l.inspired;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import w2l.inspired.web.CustomerController;

public class CustomerServletTest {

    Logger logger = LogManager.getLogger(CustomerServletTest.class);

    @Test
    void testConfig() {
        try {
            ApplicationContext context = new
                    ClassPathXmlApplicationContext("context.xml");


            CustomerController controller = context.getBean("customerController", CustomerController.class);
            Assertions.assertNotNull(controller);
        } catch (Exception e) {
            logger.error(e);
            Assertions.fail();
        }

    }
}
