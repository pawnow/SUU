package pl.edu.agh.ki.suu.mongo.consumer;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import pl.edu.agh.ki.suu.common.cdm.Message;
import pl.edu.agh.ki.suu.common.consumer.AbstractClientConsumer;
import pl.edu.agh.ki.suu.common.api.Consumer;
import org.bson.Document;

import java.util.Map;

import static pl.edu.agh.ki.suu.common.api.Constants.CONFIG_KEY;

public class MongoConsumer extends AbstractClientConsumer {

    private Gson gson = new Gson();

    private Consumer consumer;

    private MongoCollection<Document> collection;

    private Class pojoType = null;

    @Override
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
            throw new IllegalStateException("Cannot start pl.edu.agh.ki.suu.mongo consumer - missing properties!");
        }
    }

    @Override
    protected Object getMessageFromQueue() {
        Document result = collection.findOneAndDelete(new Document());
        if (result == null) {
            return null;
        }
//        String config = result.getString(CONFIG_KEY);
        String payload = gson.fromJson(result.toJson(), Message.class).getPayload();
        if (pojoType == null) {
            return gson.fromJson(payload, Map.class);
        }
        return gson.fromJson(payload, pojoType);
    }
}
