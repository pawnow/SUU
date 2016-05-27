package pl.edu.agh.ki.suu.server.service.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.ki.suu.common.cdm.Message;
import pl.edu.agh.ki.suu.mongo.dao.MongoDAO;

import java.util.Optional;

import static pl.edu.agh.ki.suu.common.api.Defaults.DEFAULT_MESSAGE_COLLECTION_NAME;

@Service(value = "receiver")
public class HelloReceiver {

    @Autowired
    private MongoDAO mongoDAO;

    public void receive(Message message){
        System.out.println("RECEIVER RECEIVED: " + message.getPayload());
        String queueName = Optional.of(message)
                .map(Message::getTarget)
                .map(Message.Target::getName)
                .orElse(DEFAULT_MESSAGE_COLLECTION_NAME);

        mongoDAO.enqueue(message, queueName);
    }
}
