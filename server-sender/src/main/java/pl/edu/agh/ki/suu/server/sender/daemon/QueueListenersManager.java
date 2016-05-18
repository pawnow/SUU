package pl.edu.agh.ki.suu.server.sender.daemon;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.ki.suu.common.cdm.Configuration;
import pl.edu.agh.ki.suu.mongo.dao.MongoDAO;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;

import static pl.edu.agh.ki.suu.common.api.Defaults.MONGO_CHECKIN_INTERVAL;

@Component
public class QueueListenersManager implements InitializingBean {

    @Autowired
    private MongoDAO mongoDao;

    private class QueueData {
        private Timer timer = new Timer(true);
        private DaemonMessageSender queueListener;
        private Set<Configuration> clients = new ConcurrentSkipListSet<>();
    }

    private Map<String, QueueData> queues = new HashMap<>();

    public void addNewClient(String queueName, Configuration consumerConfiguration) {
        addQueueIfAbsent(queueName);
        queues.get(queueName).clients.add(consumerConfiguration);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        DaemonMessageSender.initMongoDao(mongoDao);
    }

    private void addQueueIfAbsent(String queueName) {
        if (!queues.containsKey(queueName)) {
            queues.put(queueName, createQueueData(queueName));
        }
    }

    private QueueData createQueueData(String queueName) {
        QueueData queueData = new QueueData();
        DaemonMessageSender daemonMessageSender = new DaemonMessageSender(queueName, queueData.clients);
        queueData.queueListener = daemonMessageSender;
        queueData.timer.scheduleAtFixedRate(daemonMessageSender, MONGO_CHECKIN_INTERVAL, MONGO_CHECKIN_INTERVAL);
        return queueData;
    }

}
