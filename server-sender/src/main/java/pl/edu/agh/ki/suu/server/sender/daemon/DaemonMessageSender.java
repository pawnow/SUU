package pl.edu.agh.ki.suu.server.sender.daemon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.edu.agh.ki.suu.common.cdm.Configuration;
import pl.edu.agh.ki.suu.common.cdm.Message;
import pl.edu.agh.ki.suu.server.sender.service.MessageSender;
import pl.edu.agh.ki.suu.server.sender.service.MessageSenderFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class DaemonMessageSender{

    private static final int PERIOD_TIME = 6000;
    private Set<Configuration> registeredClients;

    @Autowired
    private ClientRepository clientRepository;

    @Scheduled(fixedRate = PERIOD_TIME)
    public void run() {
        registeredClients = clientRepository.getAll();
        if(registeredClients.size() > 0){
            for(Configuration client : registeredClients){
                sendMessages(client);
            }
        }

    }

    private List<Message> loadMessage(Configuration client){
        // TODO load message from database for client

        return createMySimpleMessages(client);
    }

    private List<Message> createMySimpleMessages(Configuration client){
        final Message message = new Message();
        message.setTimeout("12345");
        message.setPayload("Message Send From Server in: " + client.getProtocolVersion() + " format");
        List<Message> messages = new ArrayList<>();
        messages.add(message);
        return messages;
    }

    private void sendMessages(Configuration client){
        List<Message> messages = loadMessage(client);
        MessageSender messageSender = MessageSenderFactory.getMessageSender(client.getProtocolVersion());
        for(Message message : messages){
            messageSender.send(message, client);
        }
    }
}
