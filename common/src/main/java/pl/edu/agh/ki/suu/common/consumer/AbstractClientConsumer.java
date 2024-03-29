package pl.edu.agh.ki.suu.common.consumer;

import pl.edu.agh.ki.suu.common.api.Consumer;
import pl.edu.agh.ki.suu.common.api.ConsumerClient;

import static pl.edu.agh.ki.suu.common.api.Defaults.MONGO_CHECKIN_INTERVAL;

public abstract class AbstractClientConsumer implements ConsumerClient {

    protected Consumer consumer;

    protected void run() {
        validateConfiguration();
        Runnable r = () -> {
            try {
                while (true) {
                    Object message = getMessageFromQueue();
                    if (message != null) {
                        consumer.consume(message);
                    }
                    Thread.sleep(MONGO_CHECKIN_INTERVAL);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        new Thread(r).start();
    }

    protected abstract Object getMessageFromQueue();

    private void validateConfiguration() {
        if (consumer == null) throw new IllegalStateException("Consumer cannot be null!");
    }
}
