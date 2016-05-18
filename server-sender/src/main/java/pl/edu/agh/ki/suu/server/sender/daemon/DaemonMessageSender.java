package pl.edu.agh.ki.suu.server.sender.daemon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.edu.agh.ki.suu.common.cdm.Configuration;
import pl.edu.agh.ki.suu.common.cdm.Message;
import pl.edu.agh.ki.suu.mongo.dao.MongoDAO;
import pl.edu.agh.ki.suu.server.sender.service.MessageSender;
import pl.edu.agh.ki.suu.server.sender.service.MessageSenderFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static pl.edu.agh.ki.suu.common.api.Defaults.CLIENT_QUEUEU_NAME;
import static pl.edu.agh.ki.suu.common.api.Defaults.DEFAULT_MESSAGE_COLLECTION_NAME;
import static pl.edu.agh.ki.suu.common.api.Defaults.PERIOD_TIME;

@Component
public class DaemonMessageSender{

    @Autowired
    private MongoDAO mongoDAO;

    @Scheduled(fixedRate = PERIOD_TIME)
    public void run() {
        sendMessages();
    }

    private List<Configuration> getAllClients(){
        List<Configuration> clients = null;

        try {
            clients = mongoDAO.getAllClients(CLIENT_QUEUEU_NAME);
        }catch (NullPointerException e){

        }
        return clients;
    }

    private List<Message> loadMessage(){
        List<Message> messages = new ArrayList<>();
        Message message = null;
        try {
            while(true){
                message= mongoDAO.dequeue(DEFAULT_MESSAGE_COLLECTION_NAME);
                messages.add(message);
            }
        }catch (NullPointerException e) {

        }
        return messages;
    }

    private void sendMessages(){
        List<Configuration> clients = getAllClients();
        boolean sendFlag = false;
        if(clients != null){
            List<Message> messages = loadMessage();
            for(Message message : messages){
                sendFlag = false;
                for(Configuration client : clients){
                    if(message.getTarget().getName().equals(client.getSender().getName())){
                        MessageSender messageSender = MessageSenderFactory.getMessageSender(message.getProtocolVersion());
                        messageSender.send(message);
                        sendFlag=true;
                    }
                }
                if(!sendFlag){
                    mongoDAO.enqueue(message, DEFAULT_MESSAGE_COLLECTION_NAME);
                }

            }
        }
    }
}
