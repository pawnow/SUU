package pl.edu.agh.ki.suu.client.jms.service;

import org.springframework.stereotype.Service;
import pl.edu.agh.ki.suu.common.cdm.Message;
import pl.edu.agh.ki.suu.common.cdm.Transformer;

import javax.xml.bind.JAXBException;

@Service(value = "receiver")
public class MessageReceiver {

    public void receive(Message message){
        System.out.println("Received message!" + message.getPayload());
    }

}
