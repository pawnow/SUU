package pl.edu.agh.ki.suu.server.sender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.edu.agh.ki.suu.mongo.dao.MongoDAO;

/**
 * Created by mariuszz on 2016-05-18.
 */
@SpringBootApplication
@EnableAutoConfiguration
public class SuuSenderApp {

    @Bean
    public MongoDAO mongoDAO() { return new MongoDAO(); }

    public static void main(String[] args){
        SpringApplication.run(SuuSenderApp.class);
    }
}
