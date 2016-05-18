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

    public void sendJmsMessage(String protocol, String targetAddress, String targetName){
        final Message message = new Message();
        final Message.Target target = new Message.Target();
        target.setAddress(targetAddress);
        target.setName(targetName);
        message.setSender(new Message.Sender());
        message.setTarget(target);
        message.setTimeout("12345");
        message.setPayload("LOCALJMS");
        message.setProtocolVersion(protocol);
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(JMS_BROKER_URL);
        MessageCreator messageCreator = session -> session.createObjectMessage(message);
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.send("messages", messageCreator);
    }

    public void registerJMSClient(String operation, String senderAddress, String senderName){
        final Configuration configuration = new Configuration();
        final Configuration.Sender sender = new Configuration.Sender();
        sender.setAddress(senderAddress);
        sender.setName(senderName);
        configuration.setSender(sender);
        configuration.setProtocolVersion("JMS");
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(JMS_BROKER_URL);
        MessageCreator messageCreator = session -> session.createObjectMessage(configuration);
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.send(operation, messageCreator);
    }



}
