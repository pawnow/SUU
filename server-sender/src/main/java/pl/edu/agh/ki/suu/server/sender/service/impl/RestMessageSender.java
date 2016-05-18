package pl.edu.agh.ki.suu.server.sender.service.impl;

import org.springframework.web.client.RestTemplate;
import pl.edu.agh.ki.suu.common.cdm.Configuration;
import pl.edu.agh.ki.suu.common.cdm.Message;
import pl.edu.agh.ki.suu.server.sender.service.MessageSender;
import pl.edu.agh.ki.suu.server.sender.service.SendingFailedException;

public class RestMessageSender implements MessageSender {

    @Override
    public void send(Message message, Configuration client) {
        System.out.println("Send Message: " + message.getPayload() + " to: " + message.getTarget().getName() + " " + message.getTarget().getAddress() + " using REST");
        String url = prepareUrl(client);
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.postForObject(url, message, String.class);
        }catch (Exception e){
            throw new SendingFailedException();
        }
    }

    private String prepareUrl(Configuration client) {
        return client.getSender().getAddress();
    }
}
