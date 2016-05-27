package pl.edu.agh.ki.suu.client.jms.service;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("jms:queue:client-jms").to("receiver");
    }
}
