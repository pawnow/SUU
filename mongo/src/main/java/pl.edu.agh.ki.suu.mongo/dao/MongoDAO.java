package pl.edu.agh.ki.suu.mongo.dao;

import com.google.gson.Gson;
import com.mongodb.client.MongoIterable;
import org.bson.Document;
import pl.edu.agh.ki.suu.common.cdm.Configuration;
import pl.edu.agh.ki.suu.common.cdm.Message;
import pl.edu.agh.ki.suu.mongo.dbaccess.MongoCollectionRetriever;

import java.util.*;

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

    @SuppressWarnings("unchecked")
    public void enqueue(Message message, String queueName) {
        collectionRetriever
                .getMongoCollection(dbname, queueName)
                .insertOne(new Document(gson.fromJson(gson.toJson(message), Map.class)));
    }

    public Message dequeue(String queueName) {
        Document message = collectionRetriever.getMongoCollection(dbname, queueName).findOneAndDelete(new Document());
        return message == null ? null : gson.fromJson(message.toJson(), Message.class);
    }

    @SuppressWarnings("unchecked")
    public void register(Configuration configuration, String queueName){
        collectionRetriever
                .getMongoCollection(dbname, queueName)
                .insertOne(new Document(gson.fromJson(gson.toJson(configuration), Map.class)));
    }

    public void unregister(Configuration configuration, String queueName){
        // TODO remove configuration from mongo
    }

    public Set<Configuration> getAllClients(String queueName) {
        Set<Configuration> clients = new HashSet<>();
        MongoIterable<Document> iterableResults =  collectionRetriever.getMongoCollection(dbname, queueName).find();
        for (Document configDocument: iterableResults) {
            clients.add(gson.fromJson(configDocument.toJson(), Configuration.class));
        }
        return clients;
    }
}
