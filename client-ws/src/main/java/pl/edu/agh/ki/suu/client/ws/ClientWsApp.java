package pl.edu.agh.ki.suu.client.ws;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.web.client.RestTemplate;
import pl.edu.agh.ki.suu.common.cdm.Configuration;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

@SpringBootApplication
@EnableJms
@EnableAutoConfiguration
public class ClientWsApp {

    public static void main(String[] args) {
        SpringApplication.run(ClientWsApp.class, args);
        registerClient();
    }

    private static void registerClient(){
        RestTemplate restTemplate = new RestTemplate();
        final Configuration configuration = new Configuration();
        final Configuration.Sender sender = new Configuration.Sender();
        sender.setAddress("http://localhost:9090/ws");
        sender.setName("messages");
        configuration.setSender(sender);
        configuration.setProtocolVersion("SOAP");
        restTemplate.postForObject("http://localhost:8080/suu/register", configuration, String.class);
    }

}
