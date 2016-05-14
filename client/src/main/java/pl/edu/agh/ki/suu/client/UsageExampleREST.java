package pl.edu.agh.ki.suu.client;

import org.springframework.web.client.RestTemplate;
import pl.edu.agh.ki.suu.common.cdm.Configuration;
import pl.edu.agh.ki.suu.common.cdm.Message;

public class UsageExampleREST {

    public static void main(String[] args){
        String message = "HelloRESTCAMELAPI";
        sendRestMessage(message);
        registerClient();
    }

    private static void sendRestMessage(String string){
        RestTemplate restTemplate = new RestTemplate();
        final Message message = new Message();
        message.setSender(new Message.Sender());
        message.setTimeout("12345");
        message.setPayload(string);
        restTemplate.postForObject("http://localhost:8080/suu/message", message, String.class);
    }

    private static void registerClient(){
        RestTemplate restTemplate = new RestTemplate();
        final Configuration configuration = new Configuration();
        final Configuration.Sender sender = new Configuration.Sender();
        sender.setAddress("qwerty");
        sender.setName("asd");
        configuration.setSender(sender);
        configuration.setProtocolVersion("JMS");
        restTemplate.postForObject("http://localhost:8080/suu/register", configuration, String.class);
    }

}
