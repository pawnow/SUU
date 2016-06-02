package pl.edu.agh.ki.suu.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.annotation.EnableJms;
import pl.edu.agh.ki.suu.common.cdm.Message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableJms
@EnableAutoConfiguration
public class Client {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Client.class, args);
        UsageExampleJMS usageExampleJMS = applicationContext.getBean(UsageExampleJMS.class);
        UsageExampleSoap usageExampleSoap = applicationContext.getBean(UsageExampleSoap.class);
        UsageExampleREST usageExampleREST = applicationContext.getBean(UsageExampleREST.class);

        usageExampleSoap.sendSoapRequests(createMessages(" | WS sender"));
        usageExampleJMS.sendMessages(createMessages(" | JMS sender"));
        usageExampleREST.sendMessages(createMessages(" | REST sender"));

//        usageExampleJMS.sendJmsMessage("JMS", "example_address", "jms1");
//        usageExampleJMS.registerJMSClient("register", "example_address", "jms1");
        System.out.println("Done.");
    }

    private static List<Message> createMessages(String payloadSuffix) {
        List<Message> result = new ArrayList<>();
        List<String> queueNames = Arrays.asList("messages-jms", "messages-ws", "messages-rest", "messages-mail");

        for(int i = 1; i <= 10; i++){
            for(String name : queueNames){
                final Message message = new Message();
                final Message.Target target = new Message.Target();
                target.setAddress("example_address");
                target.setName(name);
                message.setSender(new Message.Sender());
                message.setTarget(target);
                message.setTimeout("12345");
                message.setPayload("Message number " + i + payloadSuffix);
                result.add(message);
            }
        }
        return result;
    }

}
