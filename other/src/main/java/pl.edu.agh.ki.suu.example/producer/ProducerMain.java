package pl.edu.agh.ki.suu.example.producer;

import pl.edu.agh.ki.suu.example.BusinessData;
import pl.edu.agh.ki.suu.mongo.factories.MongoMessageProducerFactory;
import pl.edu.agh.ki.suu.mongo.producer.MongoMessageProducer;

public class ProducerMain {
    public static void main(String[] args) throws InterruptedException {
        MongoMessageProducer messageProducer = MongoMessageProducerFactory.createMongoProducer();
        int i = 0;
        while (true) {
            BusinessData businessData = new BusinessData(i++);
            messageProducer.push(businessData);
            Thread.sleep(5000);
        }
    }
}
