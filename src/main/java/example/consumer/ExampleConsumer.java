package example.consumer;

import common.api.Consumer;
import example.Message;

public class ExampleConsumer implements Consumer {
    @Override
    public void consume(Object configuration) {
        Message m = (Message) configuration;
        System.out.println("I received following message: " + m.getI());
    }
}
