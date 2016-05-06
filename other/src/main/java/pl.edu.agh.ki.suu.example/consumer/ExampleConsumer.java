package pl.edu.agh.ki.suu.example.consumer;

import pl.edu.agh.ki.suu.common.api.Consumer;
import pl.edu.agh.ki.suu.example.Message;

public class ExampleConsumer implements Consumer<Message> {

    @Override
    public void consume(Message configuration) {
        System.out.println("I received following message: " + configuration.getI());
    }
}
