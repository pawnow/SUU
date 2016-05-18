package pl.edu.agh.ki.suu.client;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import pl.edu.agh.ki.suu.common.cdm.Message;

import javax.jms.ConnectionFactory;

@Service
public class UsageExampleJMS {

    private static final String JMS_BROKER_URL = "tcp://localhost:61616";
//    private static final String JMS_BROKER_URL = "tcp://localhost:56789";

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

}
