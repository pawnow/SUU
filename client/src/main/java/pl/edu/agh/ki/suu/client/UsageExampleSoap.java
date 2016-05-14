package pl.edu.agh.ki.suu.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import pl.edu.agh.ki.suu.common.cdm.Message;

@Component
public class UsageExampleSoap {

    @Autowired
    private WebServiceTemplate webServiceTemplate;

    public void sendSoapRequest() {
        final Message message = new Message();
        message.setSender(new Message.Sender());
        message.setTimeout("12345");
        message.setPayload("LOCALSOAP");
        webServiceTemplate.marshalSendAndReceive(message);
    }
}

