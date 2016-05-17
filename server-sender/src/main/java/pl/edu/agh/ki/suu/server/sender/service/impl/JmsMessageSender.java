package pl.edu.agh.ki.suu.server.sender.service.impl;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import pl.edu.agh.ki.suu.common.cdm.Configuration;
import pl.edu.agh.ki.suu.common.cdm.Message;
import pl.edu.agh.ki.suu.server.sender.service.MessageSender;

import javax.jms.ConnectionFactory;

public class JmsMessageSender implements MessageSender{

    @Override
    public void send(Message message, Configuration client) {
        String jms_broker_url = prepareJmsBrokerUrl(message, client);
        String destination = prepareDestination(message, client);
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(jms_broker_url);
        MessageCreator messageCreator = session -> session.createObjectMessage(message);
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.send(destination, messageCreator);
    }

    private String prepareJmsBrokerUrl(Message message, Configuration configuration){
        // TODO create jms_broker_url using message and configuration
        return "tcp://localhost:61616";
    }

    private String prepareDestination(Message message, Configuration configuration){
        // TODO create destination string using message and configuration
        return "messages";
    }
}
