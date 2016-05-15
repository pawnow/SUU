package pl.edu.agh.ki.suu.server.service;

import org.springframework.stereotype.Service;
import pl.edu.agh.ki.suu.common.cdm.Message;
import pl.edu.agh.ki.suu.common.cdm.Transformer;
import pl.edu.agh.ki.suu.mongo.dao.MongoDAO;

import javax.xml.bind.JAXBException;

@Service(value = "receiver")
public class HelloReceiver {

    private MongoDAO dao = new MongoDAO();
    private Transformer transformer = new Transformer();

    public void receive(Message message){
        System.out.println("RECEIVER RECEIVED (PAYLOAD): " + message.getPayload());
        dao.enqueue(message, "hello_queue");
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
