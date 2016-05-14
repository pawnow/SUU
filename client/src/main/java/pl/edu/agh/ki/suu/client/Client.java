package pl.edu.agh.ki.suu.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
@EnableAutoConfiguration
public class Client {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Client.class, args);
        UsageExampleJMS usageExampleJMS = applicationContext.getBean(UsageExampleJMS.class);
        UsageExampleSoap usageExampleSoap = applicationContext.getBean(UsageExampleSoap.class);
        usageExampleSoap.sendSoapRequest();
        usageExampleJMS.sendJmsMessage();
    }

}
