package pl.edu.agh.ki.suu.client.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.client.RestTemplate;
import pl.edu.agh.ki.suu.common.cdm.Configuration;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

@SpringBootApplication
@EnableJms
@EnableAutoConfiguration
public class ClientJmsApp {

    private static final String JMS_BROKER_URL = "tcp://localhost:61616";

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("client-jms");
    }

    @Bean
    JmsListenerContainerFactory<?> myJmsContainerFactory(ConnectionFactory connectionFactory) {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

    public static void main(String[] args) {
        startJMSBroker();
        enableSerializeCustomPackages();
        ApplicationContext applicationContext = SpringApplication.run(ClientJmsApp.class, args);
        registerJmsMessage();
    }

    private static void registerJmsMessage(){
        final Configuration configuration = new Configuration();
        final Configuration.Sender sender = new Configuration.Sender();
        sender.setAddress("tcp://localhost:56789");
        sender.setName("messages-jms");
        configuration.setSender(sender);
        configuration.setSomeParameter("client-jms");
        configuration.setProtocolVersion("JMS");
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(JMS_BROKER_URL);
        MessageCreator messageCreator = session -> session.createObjectMessage(configuration);
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.send("register", messageCreator);
    }

    private static void startJMSBroker(){
        BrokerService broker = new BrokerService();
        broker.setBrokerName("Mieczyslaw");
        broker.setUseJmx(false);//getManagementContext();
        try {
            broker.addConnector("tcp://localhost:56789");
            broker.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void enableSerializeCustomPackages(){
        System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES","*");
    }

}
