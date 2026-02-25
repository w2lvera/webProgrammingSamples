package w2l.inspired.notWeb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import w2l.inspired.logical.DailyLogFileProcessor;
import w2l.inspired.model.CompletionStatus;
import w2l.inspired.model.DailyLog;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class DailyProcessorSpringTest {
    static DailyLogFileProcessor processorUnderTest;


    @BeforeAll
    static void beforeAll() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("test-config-short.xml"));
        processorUnderTest = factory.getBean("dailyLogProcessor", DailyLogFileProcessor.class);
    }


    @Test
    void testMapping() throws ParseException {

   SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
   DailyLog dailyLog = processorUnderTest.mapDailyLog(dateFormat,"23.02.26,1,DONE");
   Assertions.assertEquals(1, dailyLog.getCustomer().getId());
   //Assertions.assertEquals("some task", dailyLog.getCustomer().getDescription());
   Assertions.assertEquals(CompletionStatus.DONE, dailyLog.getStatus());
}


@Test
void testWrongStateMapping() {


   SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
   Assertions.assertThrows(IllegalArgumentException.class,
           ()-> processorUnderTest.mapDailyLog(dateFormat,"06.06.23,1,some_stupid_state"));


}


@Test
void testWrongDateMapping() {


   SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
   Assertions.assertThrows(ParseException.class,
           ()-> processorUnderTest.mapDailyLog(dateFormat,"2424.24,10,DONE"));


}


@Test
void testWrongLineMapping() {


   SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
   Assertions.assertThrows(ParseException.class,
           ()-> processorUnderTest.mapDailyLog(dateFormat,"not three parts separated by comma"));


}


    @Test
    void testFileRead() throws IOException {
        List<DailyLog> dailyLog = processorUnderTest.getLog();
        Assertions.assertEquals(3,dailyLog.size());
    }

}
