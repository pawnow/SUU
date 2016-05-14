package pl.edu.agh.ki.suu.server.service.sender.impl;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import pl.edu.agh.ki.suu.common.cdm.Configuration;
import pl.edu.agh.ki.suu.common.cdm.Message;
import pl.edu.agh.ki.suu.server.service.sender.MessageSender;

public class SoapMessageSender implements MessageSender{

    @Override
    public void send(Message message, Configuration client) {
        String soap_uri = prepareSoapUri(message, client);
        WebServiceTemplate webServiceTemplate = webServiceTemplate(soap_uri);
        webServiceTemplate.marshalSendAndReceive(message);
    }

    private String prepareSoapUri(Message message, Configuration client){
        // TODO create soap uri using message, client
        return "tcp://localhost:61616";
    }

    public WebServiceTemplate webServiceTemplate(String soapUri){
        WebServiceTemplate template = new WebServiceTemplate();
        template.setDefaultUri(soapUri);
        template.setMarshaller(messageMarshaller());
        return template;
    }

    private Jaxb2Marshaller messageMarshaller(){
        final Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(Message.class);
        return marshaller;
    }
}
