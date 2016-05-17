package pl.edu.agh.ki.suu.server.sender.service.impl;

import org.springframework.web.client.RestTemplate;
import pl.edu.agh.ki.suu.common.cdm.Configuration;
import pl.edu.agh.ki.suu.common.cdm.Message;
import pl.edu.agh.ki.suu.server.sender.service.MessageSender;

public class RestMessageSender implements MessageSender {

    @Override
    public void send(Message message, Configuration client) {
        String url = prepareUrl(message, client);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, message, String.class);
    }

    private String prepareUrl(Message message, Configuration client){
        // TODO create url using message.getTarget().getAddress() or client.getSender().getAddress()
        return "http://localhost:8080/suu/message";
    }
}
