package pl.edu.agh.ki.suu.server.service;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("jms:queue:messages").to("receiver");
        from("jms:queue:register").to("register");
        from("jms:queue:unregister").to("unregister");
    }
}
