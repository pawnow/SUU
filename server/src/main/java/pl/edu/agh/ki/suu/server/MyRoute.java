package pl.edu.agh.ki.suu.server;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("jms:queue:messages").to("receiver");
        /*from("jetty:http://localhost:8080/suu/message").log("Received a request").to("bean:HelloReceiver");*/
        /*from("jetty://http://localhost:8888/greeting")
                .log("Received a request")
                .setBody(simple("Hello, world!"));*/
    }
}
