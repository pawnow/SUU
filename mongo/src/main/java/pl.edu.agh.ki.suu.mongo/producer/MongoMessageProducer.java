package pl.edu.agh.ki.suu.mongo.producer;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import pl.edu.agh.ki.suu.common.consumer.AbstractClientProducer;

import static pl.edu.agh.ki.suu.common.api.Constants.CONFIG_KEY;


public class MongoMessageProducer extends AbstractClientProducer {
    private MongoCollection<Document> collection;
    private Gson gson = new Gson();

    @Override
    public void push(Object message) {
        collection.insertOne(new Document(CONFIG_KEY, gson.toJson(createMessage(gson.toJson(message)))));
    }

    public void setCollection(MongoCollection<Document> collection) {
        this.collection = collection;
    }
}
