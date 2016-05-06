package example.producer;

import example.Message;
import mongo.factories.MongoMessageProducerFactory;
import mongo.producer.MongoMessageProducer;

public class ProducerMain {
    public static void main(String[] args) throws InterruptedException {
        MongoMessageProducer messageProducer = MongoMessageProducerFactory.createMongoProducer();
        int i = 0;
        while (true) {
            Message m = new Message(i++);
            messageProducer.push(m);
            Thread.sleep(5000);
        }
    }
}
