package pl.edu.agh.ki.suu.mongo.producer;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import pl.edu.agh.ki.suu.common.api.Defaults;
import pl.edu.agh.ki.suu.common.cdm.Message;
import pl.edu.agh.ki.suu.common.consumer.AbstractClientProducer;
import pl.edu.agh.ki.suu.mongo.dao.MongoDAO;

import static pl.edu.agh.ki.suu.common.api.Constants.CONFIG_KEY;


public class MongoMessageProducer extends AbstractClientProducer {
    private MongoCollection<Document> collection;
    private Gson gson = new Gson();
    private MongoDAO mongoDAO = new MongoDAO();

    @Override
    public void push(Object message) {
//        collection.insertOne(new Document(CONFIG_KEY, gson.toJson(createMessage(gson.toJson(message)))));
        Message m = new Message();
        m.setPayload(gson.toJson(message));
        mongoDAO.enqueue(m, Defaults.COLLECTION_NAME);
    }

    public void setCollection(MongoCollection<Document> collection) {
        this.collection = collection;
    }
}
