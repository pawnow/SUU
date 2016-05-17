package pl.edu.agh.ki.suu.server.sender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by mariuszz on 2016-05-18.
 */
@SpringBootApplication
@EnableAutoConfiguration
public class SuuSenderApp {

    public static void main(String[] args){
        SpringApplication.run(SuuSenderApp.class);
    }
}
