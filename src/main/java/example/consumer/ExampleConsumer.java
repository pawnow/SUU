package example.consumer;

import common.api.Consumer;
import example.Message;

public class ExampleConsumer implements Consumer<Message> {

    @Override
    public void consume(Message configuration) {
        System.out.println("I received following message: " + configuration.getI());
    }
}
