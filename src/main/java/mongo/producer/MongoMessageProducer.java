package mongo.producer;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import common.api.Constants;
import common.api.ProducerClient;
import org.bson.Document;

import static common.api.Constants.CONFIG_KEY;


public class MongoMessageProducer implements ProducerClient {
    private MongoCollection<Document> collection;
    private Gson gson = new Gson();

    @Override
    public void push(Object message) {
        collection.insertOne(new Document(CONFIG_KEY, gson.toJson(message)));
    }

    public void setCollection(MongoCollection<Document> collection) {
        this.collection = collection;
    }
}
