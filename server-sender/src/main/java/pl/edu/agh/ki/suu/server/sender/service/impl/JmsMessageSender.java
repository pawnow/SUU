package pl.edu.agh.ki.suu.server.sender.service.impl;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import pl.edu.agh.ki.suu.common.cdm.Configuration;
import pl.edu.agh.ki.suu.common.cdm.Message;
import pl.edu.agh.ki.suu.server.sender.service.MessageSender;
import pl.edu.agh.ki.suu.server.sender.service.SendingFailedException;

import javax.jms.ConnectionFactory;

public class JmsMessageSender implements MessageSender {

    @Override
    public void send(Message message, Configuration client) {
        System.out.println("Send Message To: " + message.getTarget().getName() + " " + message.getTarget().getAddress() + " using JMS");

        String jms_broker_url = prepareJmsBrokerUrl(client);
        String destination = prepareDestination(client);

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(jms_broker_url);
        MessageCreator messageCreator = session -> session.createObjectMessage(message);
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);

        try {
            jmsTemplate.send(destination, messageCreator);
        }catch (Exception e){
            throw new SendingFailedException();
        }
    }

    private String prepareJmsBrokerUrl(Configuration client) {
        return client.getSender().getAddress();
    }

    private String prepareDestination(Configuration client) {
        /*
        In JMS we must have destination(queueName in client application)
        so i assume that client send destination in Configuration.someParameter field
         */
        return client.getSomeParameter();
    }
}
