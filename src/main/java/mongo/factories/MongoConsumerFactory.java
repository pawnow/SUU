package mongo.factories;

import common.api.Consumer;
import mongo.consumer.MongoConsumer;
import mongo.dbaccess.MongoCollectionRetriever;

import static common.api.Defaults.COLLECTION_NAME;
import static common.api.Defaults.DB_NAME;

public class MongoConsumerFactory {
    public static MongoConsumer createMongoConsumer(Consumer consumer) {
        MongoCollectionRetriever collectionRetriever = new MongoCollectionRetriever();
        MongoConsumer mongoConsumer = new MongoConsumer();
        mongoConsumer.setCollection(collectionRetriever.getMongoCollection(DB_NAME, COLLECTION_NAME));
        mongoConsumer.setConsumer(consumer);
        return mongoConsumer;
    }

    public static MongoConsumer createMongoConsumer(Consumer consumer, String host, int port, String dbName, String collectionName, Class pojo) {
        MongoCollectionRetriever collectionRetriever = new MongoCollectionRetriever(host, port);
        MongoConsumer mongoConsumer = new MongoConsumer();
        mongoConsumer.setCollection(collectionRetriever.getMongoCollection(dbName, collectionName));
        mongoConsumer.setConsumer(consumer);
        mongoConsumer.setPojoType(pojo);
        return mongoConsumer;
    }
}
