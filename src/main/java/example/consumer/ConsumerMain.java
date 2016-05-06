package example.consumer;

import common.api.Consumer;
import example.Message;
import mongo.consumer.MongoConsumer;
import mongo.factories.MongoConsumerFactory;

import static common.api.Defaults.*;

public class ConsumerMain {
    public static void main(String[] args) {
        Consumer worker = new ExampleConsumer();
        MongoConsumer consumer = MongoConsumerFactory.createMongoConsumer(worker, HOST, PORT, DB_NAME, COLLECTION_NAME, Message.class);
        consumer.start();
    }
}
