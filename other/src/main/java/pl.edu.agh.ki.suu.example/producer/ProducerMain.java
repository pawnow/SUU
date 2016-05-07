package pl.edu.agh.ki.suu.example.producer;

import pl.edu.agh.ki.suu.common.cdm.Message;
import pl.edu.agh.ki.suu.mongo.factories.MongoMessageProducerFactory;
import pl.edu.agh.ki.suu.mongo.producer.MongoMessageProducer;

public class ProducerMain {
    public static void main(String[] args) throws InterruptedException {
        MongoMessageProducer messageProducer = MongoMessageProducerFactory.createMongoProducer();
        int i = 0;
        while (true) {
            Message m = new Message();
            m.setPayload("This is yet another message with some number: " + i++);
            messageProducer.push(m);
            Thread.sleep(5000);
        }
    }
}
