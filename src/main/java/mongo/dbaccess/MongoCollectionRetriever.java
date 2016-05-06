package mongo.dbaccess;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import static common.api.Defaults.HOST;
import static common.api.Defaults.PORT;

public class MongoCollectionRetriever {
    private MongoClient mongoClient;

    public MongoCollectionRetriever(String host, int port) {
        crateMongoClient(host, port);
    }

    public MongoCollectionRetriever() {
        crateMongoClient(HOST, PORT);
    }

    private void crateMongoClient(String host, int port) {
        mongoClient = new MongoClient(host, port);
    }

    public MongoCollection<Document> getMongoCollection(String dbname, String collectionName) {
        return mongoClient.getDatabase(dbname).getCollection(collectionName);
    }
}
