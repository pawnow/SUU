package pl.edu.agh.ki.suu.client.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.web.client.RestTemplate;
import pl.edu.agh.ki.suu.common.cdm.Configuration;

@SpringBootApplication
@EnableJms
@EnableAutoConfiguration
public class ClientRestApp {

    public static void main(String[] args) {
        SpringApplication.run(ClientRestApp.class, args);
        registerClient();
    }

    private static void registerClient(){
        RestTemplate restTemplate = new RestTemplate();
        final Configuration configuration = new Configuration();
        final Configuration.Sender sender = new Configuration.Sender();
        sender.setAddress("http://localhost:7070/rest");
        sender.setName("messages-rest");
        configuration.setSender(sender);
        configuration.setProtocolVersion("REST");
        restTemplate.postForObject("http://localhost:8080/suu/register", configuration, String.class);
    }

}
