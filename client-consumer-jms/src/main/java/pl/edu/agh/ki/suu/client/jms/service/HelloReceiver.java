package pl.edu.agh.ki.suu.client.jms.service;

import org.springframework.stereotype.Service;
import pl.edu.agh.ki.suu.common.cdm.Message;
import pl.edu.agh.ki.suu.common.cdm.Transformer;

import javax.xml.bind.JAXBException;

@Service(value = "receiver")
public class HelloReceiver {

    private Transformer transformer = new Transformer();

    public void receive(Message message){
        System.out.println("RECEIVER RECEIVED (PAYLOAD): " + message.getPayload());
        System.out.println("RECEIVER RECEIVED (FULL): " + message2String(message));
    }

    private String message2String(Message message){
        String messageStr = null;
        try {
            messageStr = transformer.marshal(message);
            System.out.println("RECEIVER RECEIVED: " + messageStr);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return messageStr;
    }


}
