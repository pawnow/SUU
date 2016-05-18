package pl.edu.agh.ki.suu.server.sender.daemon;

import pl.edu.agh.ki.suu.common.cdm.Configuration;
import pl.edu.agh.ki.suu.common.cdm.Message;
import pl.edu.agh.ki.suu.mongo.dao.MongoDAO;
import pl.edu.agh.ki.suu.server.sender.service.MessageSenderFactory;
import pl.edu.agh.ki.suu.server.sender.service.SendingFailedException;

import java.util.*;

public class DaemonMessageSender extends TimerTask {

    private static MongoDAO mongoDAO;
    private static Random random = new Random();
    private Set<Configuration> clients;
    private String queueName;

    public DaemonMessageSender(String queueName, Set<Configuration> clients) {
        this.queueName = queueName;
        this.clients = clients;
    }

    public static void initMongoDao(MongoDAO mongoDAO) {
        DaemonMessageSender.mongoDAO = mongoDAO;
    }

    @Override
    public void run() {
        sendMessages();
    }


//    private List<Configuration> getAllClients() {
//        List<Configuration> clients = null;
//
//        try {
//            clients = mongoDAO.getAllClients(CLIENT_QUEUEU_NAME);
//        } catch (NullPointerException e) {
//
//        }
//        return clients;
//    }
//
//    private List<Message> loadMessage() {
//        List<Message> messages = new ArrayList<>();
//        Message message = null;
//        try {
//            while (true) {
//                message = mongoDAO.dequeue(queueName);
//                messages.add(message);
//            }
//        } catch (NullPointerException e) {
//
//        }
//        return messages;
//    }
//

    private Configuration getRandomClient() {
        if (clients == null || clients.isEmpty()) {
            return null;
        }
        int size = clients.size();
        int index = random.nextInt(size);
        int i = 0;
        for (Configuration client : clients) {
            if (i == index) {
                return client;
            }
            i++;
        }
        return null;
    }

    private void sendMessages() {
        Configuration client;
        Message message;
        if ((client = getRandomClient()) == null || (message = mongoDAO.dequeue(queueName)) == null) {
            return;
        }
        try {
            MessageSenderFactory
                    .getMessageSender(message.getProtocolVersion())
                    .send(message, client);
        } catch (SendingFailedException e) {
            mongoDAO.enqueue(message, queueName);
        }

//        List<Configuration> clients = getAllClients();
//        boolean sendFlag = false;
//        if (clients != null) {
//            List<Message> messages = loadMessage();
//            for (Message message : messages) {
//                sendFlag = false;
//                for (Configuration client : clients) {
//                    if (message.getTarget().getName().equals(client.getSender().getName())) {
//                        MessageSender messageSender = MessageSenderFactory.getMessageSender(message.getProtocolVersion());
//                        messageSender.send(message);
//                        sendFlag = true;
//                    }
//                }
//                if (!sendFlag) {
//                    mongoDAO.enqueue(message, DEFAULT_MESSAGE_COLLECTION_NAME);
//                }
//
//            }
//        }

    }
}
