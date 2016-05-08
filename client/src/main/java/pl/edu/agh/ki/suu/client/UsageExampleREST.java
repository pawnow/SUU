package pl.edu.agh.ki.suu.client;

import org.springframework.web.client.RestTemplate;
import pl.edu.agh.ki.suu.common.cdm.Message;

public class UsageExampleREST {

    public static void main(String[] args){
        String message = "HelloRESTCAMELAPI";
        sendRestMessage(message);
    }

    private static void sendRestMessage(String string){
        RestTemplate restTemplate = new RestTemplate();
        final Message message = new Message();
        message.setSender(new Message.Sender());
        message.setTimeout("12345");
        message.setPayload(string);
        restTemplate.postForObject("http://localhost:8080/suu/message", message, String.class);
    }

}
