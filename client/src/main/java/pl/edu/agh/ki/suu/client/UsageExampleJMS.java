package pl.edu.agh.ki.suu.client;

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
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import pl.edu.agh.ki.suu.common.cdm.Message;

import javax.jms.ConnectionFactory;

@SpringBootApplication
@EnableJms
@EnableAutoConfiguration
public class UsageExampleJMS {

    @Bean
    JmsListenerContainerFactory<?> myJmsContainerFactory(ConnectionFactory connectionFactory) {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(UsageExampleJMS.class, args);
        final Message message = new Message();
        message.setSender(new Message.Sender());
        message.setTimeout("12345");
        message.setPayload("LOCALJMS");
        MessageCreator messageCreator = session -> session.createObjectMessage(message);
        JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);
        System.out.println("Sending a new remote JMS message. WARNING: not working yet");
        //TODO: may not work easily: http://stackoverflow.com/questions/34607596/spring-boot-sharing-embedded-jms-broker-with-separate-service
        jmsTemplate.send("messages", messageCreator);

        UsageExampleSoap usageExampleSoap = applicationContext.getBean(UsageExampleSoap.class);
        usageExampleSoap.sendSoapRequest();
    }

    @Bean
    public WebServiceTemplate webServiceTemplate(){
        WebServiceTemplate template = new WebServiceTemplate();
        template.setDefaultUri("http://localhost:8080/ws");
        template.setMarshaller(messageMarshaller());
        return template;
    }

    @Bean
    public Jaxb2Marshaller messageMarshaller(){
        final Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(Message.class);
        return marshaller;
    }

}
