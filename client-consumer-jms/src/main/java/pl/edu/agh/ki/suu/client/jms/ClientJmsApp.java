package pl.edu.agh.ki.suu.client.jms;

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

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

@SpringBootApplication
@EnableJms
@EnableAutoConfiguration
public class ClientJmsApp {

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("messages");
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
    }

    private static void startJMSBroker(){
        BrokerService broker = new BrokerService();
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
