package mongo.consumer;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import common.consumer.AbstractClientConsumer;
import common.api.Consumer;
import org.bson.Document;

import java.util.Map;

import static common.api.Constants.CONFIG_KEY;

public class MongoConsumer extends AbstractClientConsumer {

    private Gson gson = new Gson();

    private Consumer consumer;

    private MongoCollection<Document> collection;

    private Class pojoType = null;

    public void start() {
        validateConfiguration();
        super.consumer = consumer;
        super.run();
    }

    public void setCollection(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public void setPojoType(Class pojoType) {
        this.pojoType = pojoType;
    }

    private void validateConfiguration() {
        if (consumer == null || collection == null) {
            throw new IllegalStateException("Cannot start mongo consumer - missing properties!");
        }
    }

    @Override
    protected Object getMessageFromQueue() {
        Document result = collection.findOneAndDelete(new Document());
        if (result == null) {
            return null;
        }
        String config = result.getString(CONFIG_KEY);
        if (pojoType == null) {
            return gson.fromJson(config, Map.class);
        }
        return gson.fromJson(config, pojoType);
    }
}
