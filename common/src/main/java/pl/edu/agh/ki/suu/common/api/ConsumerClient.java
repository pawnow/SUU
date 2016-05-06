package pl.edu.agh.ki.suu.common.api;

public interface ConsumerClient {
    /**
     * Starts polling messages from the queue and passes them to consumer.
     * May parse them to POJOs.
     * @param consumer
     */
    void start(Consumer consumer);
}
