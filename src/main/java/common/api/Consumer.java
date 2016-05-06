package common.api;

public interface Consumer<T> {
    void consume(T configuration);
}
