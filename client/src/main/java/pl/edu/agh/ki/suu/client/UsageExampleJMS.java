package pl.edu.agh.ki.suu.client;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.ConnectionFactory;

@SpringBootApplication
@EnableJms
public class UsageExampleJMS {

    private static final String JMS_BROKER_URL = "vm://localhost?broker.persistent=false&broker.useJmx=false";
    @Bean
    public ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory(JMS_BROKER_URL);
    }

    @Bean
    JmsListenerContainerFactory<?> myJmsContainerFactory(ConnectionFactory connectionFactory) {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(UsageExampleJMS.class, args);
        MessageCreator messageCreator = session -> session.createTextMessage("REMOTEJMS");
        JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);
        System.out.println("Sending a new remote JMS message. WARNING: not working yet");
        //TODO: may not work easily: http://stackoverflow.com/questions/34607596/spring-boot-sharing-embedded-jms-broker-with-separate-service
        jmsTemplate.send("messages", messageCreator);
    }

}
