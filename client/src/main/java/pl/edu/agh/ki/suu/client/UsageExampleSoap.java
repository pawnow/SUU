package pl.edu.agh.ki.suu.client;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import pl.edu.agh.ki.suu.common.cdm.Message;

@Service
public class UsageExampleSoap {

    private static final String REMOTE_SOAP_URI = "http://localhost:8080/ws";

    private WebServiceTemplate webServiceTemplate = webServiceTemplate();

    public void sendSoapRequest() {
        for(int i = 0; i < 10; i++) {
            final Message message = new Message();
            message.setSender(new Message.Sender());
            message.setTimeout("12345");
            message.setPayload("LOCALSOAP " + i);
            webServiceTemplate.marshalSendAndReceive(message);
        }
    }

    public WebServiceTemplate webServiceTemplate(){
        WebServiceTemplate template = new WebServiceTemplate();
        template.setDefaultUri(REMOTE_SOAP_URI);
        template.setMarshaller(messageMarshaller());
        return template;
    }

    public Jaxb2Marshaller messageMarshaller(){
        final Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(Message.class);
        return marshaller;
    }
}

