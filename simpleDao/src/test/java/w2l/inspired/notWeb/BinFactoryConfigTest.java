package w2l.inspired.notWeb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import w2l.inspired.logical.CustomersChecker;

public class BinFactoryConfigTest {
    Logger logger = LogManager.getLogger(BinFactoryConfigTest.class);
    @Test
    void testConfig(){
        try{
            DefaultListableBeanFactory factory= new DefaultListableBeanFactory();
            XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
            reader.loadBeanDefinitions(new ClassPathResource("config.xml"));
            CustomersChecker checker = factory.getBean(CustomersChecker.class);
            Assertions.assertNotNull(checker);
        }
        catch(Exception e){
            logger.error(e);
            Assertions.fail();
        }
    }

}
