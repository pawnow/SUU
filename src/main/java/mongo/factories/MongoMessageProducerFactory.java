package mongo.factories;

import common.api.Defaults;
import mongo.dbaccess.MongoCollectionRetriever;
import mongo.producer.MongoMessageProducer;

public class MongoMessageProducerFactory {
    public static MongoMessageProducer createMongoProducer() {
        MongoCollectionRetriever collectionRetriever = new MongoCollectionRetriever();
        MongoMessageProducer messageProducer = new MongoMessageProducer();
        messageProducer.setCollection(collectionRetriever.getMongoCollection(Defaults.DB_NAME, Defaults.COLLECTION_NAME));
        return messageProducer;
    }

    public static MongoMessageProducer createMongoProducer(String host, int port, String dbname, String collectionName) {
        MongoCollectionRetriever collectionRetriever = new MongoCollectionRetriever(host, port);
        MongoMessageProducer messageProducer = new MongoMessageProducer();
        messageProducer.setCollection(collectionRetriever.getMongoCollection(dbname, collectionName));
        return messageProducer;
    }
}
