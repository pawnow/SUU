package pl.edu.agh.ki.suu.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.edu.agh.ki.suu.common.cdm.Configuration;
import pl.edu.agh.ki.suu.common.cdm.Message;
import java.util.List;

@Service
public class UsageExampleREST {

    public static void main(String[] args){
        String message = "HelloRESTCAMELAPI";
        sendRestMessage(message);
        registerClient();
    }

    public void sendMessages(List<Message> messageList){
        RestTemplate restTemplate = new RestTemplate();
        for(Message message : messageList) {
            restTemplate.postForObject("http://localhost:8080/suu/message", message, String.class);
        }
    }

    private static void sendRestMessage(String string){
        RestTemplate restTemplate = new RestTemplate();
        final Message message = new Message();
        final Message.Target target = new Message.Target();
        target.setAddress("example_address");
        target.setName("rest1");
        message.setSender(new Message.Sender());
        message.setTarget(target);
        message.setProtocolVersion("REST");
        message.setTimeout("12345");
        message.setPayload(string);
        restTemplate.postForObject("http://localhost:8080/suu/message", message, String.class);
    }

    private static void registerClient(){
        RestTemplate restTemplate = new RestTemplate();
        final Configuration configuration = new Configuration();
        final Configuration.Sender sender = new Configuration.Sender();
        sender.setAddress("qwerty");
        sender.setName("rest1");
        configuration.setSender(sender);
        configuration.setProtocolVersion("REST");
        restTemplate.postForObject("http://localhost:8080/suu/register", configuration, String.class);
    }

}
