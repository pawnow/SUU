package pl.edu.agh.ki.suu.example.consumer;

import pl.edu.agh.ki.suu.example.BusinessData;
import pl.edu.agh.ki.suu.mongo.factories.MongoConsumerFactory;

import static pl.edu.agh.ki.suu.common.api.Defaults.*;

public class ConsumerMain {
    public static void main(String[] args) {
        MongoConsumerFactory
                .createMongoConsumer(new ExampleConsumer(), HOST, PORT, DB_NAME, COLLECTION_NAME, BusinessData.class)
                .start();
    }
}
