package pl.edu.agh.ki.suu.server.service.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import pl.edu.agh.ki.suu.common.cdm.Message;

import javax.jms.Queue;

@Endpoint
public class SoapReceiver {
    private static final String NAMESPACE_URI = "http://localhost:8080/ws";

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "Message")
    public void saveMessage(@RequestPayload Message message) {
        this.jmsMessagingTemplate.convertAndSend(this.queue, message);
    }
}
