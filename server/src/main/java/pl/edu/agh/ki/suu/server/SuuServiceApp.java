package pl.edu.agh.ki.suu.server;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;

@SpringBootApplication
@EnableJms
public class SuuServiceApp {

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("messages");
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(SuuServiceApp.class, args);
    }

}
