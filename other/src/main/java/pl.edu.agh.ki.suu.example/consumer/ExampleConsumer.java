package pl.edu.agh.ki.suu.example.consumer;

import pl.edu.agh.ki.suu.common.api.Consumer;
import pl.edu.agh.ki.suu.example.BusinessData;

public class ExampleConsumer implements Consumer<BusinessData> {

    @Override
    public void consume(BusinessData configuration) {
        System.out.println("I received following message: " + configuration.getI());
    }
}
