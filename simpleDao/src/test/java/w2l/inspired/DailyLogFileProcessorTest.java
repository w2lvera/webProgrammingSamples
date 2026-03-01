package w2l.inspired;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import w2l.inspired.logical.DailyLogFileProcessor;
import w2l.inspired.web.CustomerController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DailyLogFileProcessorTest {

    @Test
    public void testFileRead() throws IOException {
        ApplicationContext context = new
                ClassPathXmlApplicationContext("context.xml");


        DailyLogFileProcessor processor = context.getBean("dailyLogFileProcessor", DailyLogFileProcessor.class);
        Assertions.assertEquals(4,processor.getLog().size());
    }


}
