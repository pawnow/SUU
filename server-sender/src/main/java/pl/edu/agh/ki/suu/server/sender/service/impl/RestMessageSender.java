package pl.edu.agh.ki.suu.server.sender.service.impl;

import org.springframework.web.client.RestTemplate;
import pl.edu.agh.ki.suu.common.cdm.Configuration;
import pl.edu.agh.ki.suu.common.cdm.Message;
import pl.edu.agh.ki.suu.server.sender.service.MessageSender;

public class RestMessageSender implements MessageSender {

    @Override
    public void send(Message message) {
        System.out.println("Send Message: " + message.getPayload() + " to: " + message.getTarget().getName() + " " + message.getTarget().getAddress() + " using REST");
//        String url = prepareUrl(message);
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.postForObject(url, message, String.class);
    }

    private String prepareUrl(Message message){
        // TODO create url using message.getTarget().getAddress() or client.getSender().getAddress()
        return message.getTarget().getAddress();
    }
}
