package pl.edu.agh.ki.suu.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SuuServiceApp {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(SuuServiceApp.class, args);
    }

}
