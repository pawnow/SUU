package pl.edu.agh.ki.suu.mongo.dao;

import com.google.gson.Gson;
import org.bson.Document;
import pl.edu.agh.ki.suu.common.cdm.Configuration;
import pl.edu.agh.ki.suu.common.cdm.Message;
import pl.edu.agh.ki.suu.mongo.dbaccess.MongoCollectionRetriever;

import java.util.ArrayList;
import java.util.List;
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
        enqueue(message, message.getTarget().getName());
    }

    @SuppressWarnings("unchecked")
    public void enqueue(Message message, String queueName) {
        collectionRetriever
                .getMongoCollection(dbname, queueName)
                .insertOne(new Document(gson.fromJson(gson.toJson(message), Map.class)));
    }

    public Message dequeue(String queueName) {
        return gson.fromJson(collectionRetriever.getMongoCollection(dbname, queueName).findOneAndDelete(new Document()).toJson(), Message.class);
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

    public List<Configuration> getAllClients(String queueName){
        List<Document> documents = (List<Document>) collectionRetriever
                .getMongoCollection(dbname, queueName).find()
                .into(new ArrayList<Document>());
        List<Configuration> clients = new ArrayList<>();
        for(Document doc : documents){
            clients.add(gson.fromJson(doc.toJson(), Configuration.class));
        }
        return clients;
    }
}
