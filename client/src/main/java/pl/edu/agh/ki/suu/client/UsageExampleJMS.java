package pl.edu.agh.ki.suu.client;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import pl.edu.agh.ki.suu.common.cdm.Configuration;
import pl.edu.agh.ki.suu.common.cdm.Message;

import javax.jms.ConnectionFactory;

@Service
public class UsageExampleJMS {

    private static final String JMS_BROKER_URL = "tcp://localhost:61616";

    public void sendJmsMessage(){
        final Message message = new Message();
        message.setSender(new Message.Sender());
        message.setTimeout("12345");
        message.setPayload("LOCALJMS");
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(JMS_BROKER_URL);
        MessageCreator messageCreator = session -> session.createObjectMessage(message);
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.send("messages", messageCreator);
    }

    public void processJmsClient(String operation, String senderAddress){
        final Configuration configuration = new Configuration();
        final Configuration.Sender sender = new Configuration.Sender();
        sender.setAddress(senderAddress);
        configuration.setSender(sender);
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(JMS_BROKER_URL);
        MessageCreator messageCreator = session -> session.createObjectMessage(configuration);
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.send(operation, messageCreator);
    }



}
