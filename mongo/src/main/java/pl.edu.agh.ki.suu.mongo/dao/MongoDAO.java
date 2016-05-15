package pl.edu.agh.ki.suu.mongo.dao;

import com.google.gson.Gson;
import org.bson.Document;
import pl.edu.agh.ki.suu.common.cdm.Message;
import pl.edu.agh.ki.suu.mongo.dbaccess.MongoCollectionRetriever;

import java.util.Map;

import static pl.edu.agh.ki.suu.common.api.Defaults.DB_NAME;
import static pl.edu.agh.ki.suu.common.api.Defaults.HOST;
import static pl.edu.agh.ki.suu.common.api.Defaults.PORT;

public class MongoDAO {

    private MongoCollectionRetriever collectionRetriever = null;
    private Gson gson = new Gson();

    private String dbname = DB_NAME;

    public MongoDAO() {
        this.collectionRetriever = new MongoCollectionRetriever(HOST, PORT);
    }

    public MongoDAO(String host, int port) {
        this.collectionRetriever = new MongoCollectionRetriever(host, port);
    }

    public void enqueue(Message message) {
        //TODO: add null check
        enqueue(message, message.getTarget().getName());
    }

    public void enqueue(Message message, String queueName) {
        collectionRetriever
                .getMongoCollection(dbname, queueName)
                .insertOne(new Document(gson.fromJson(gson.toJson(message), Map.class)));
    }

    public Message dequeue(String queueName) {
        return gson.fromJson(collectionRetriever.getMongoCollection(dbname, queueName).findOneAndDelete(new Document()).toJson(), Message.class);
    }
}
