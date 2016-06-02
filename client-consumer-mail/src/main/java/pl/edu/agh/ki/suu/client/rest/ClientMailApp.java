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
public class ClientMailApp {

    public static void main(String[] args) {
        SpringApplication.run(ClientMailApp.class, args);
        registerClient();
    }

    private static void registerClient(){
        RestTemplate restTemplate = new RestTemplate();
        final Configuration configuration = new Configuration();
        final Configuration.Sender sender = new Configuration.Sender();
        sender.setAddress("mateuszbiedak@gmail.com");
        sender.setName("messages-mail");
        configuration.setSender(sender);
        configuration.setProtocolVersion("MAIL");
        restTemplate.postForObject("http://localhost:8080/suu/register", configuration, String.class);
    }

}
