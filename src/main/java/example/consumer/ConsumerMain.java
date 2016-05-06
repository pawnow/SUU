package example.consumer;

import example.Message;
import mongo.factories.MongoConsumerFactory;

import static common.api.Defaults.*;

public class ConsumerMain {
    public static void main(String[] args) {
        MongoConsumerFactory
                .createMongoConsumer(new ExampleConsumer(), HOST, PORT, DB_NAME, COLLECTION_NAME, Message.class)
                .start();
    }
}
