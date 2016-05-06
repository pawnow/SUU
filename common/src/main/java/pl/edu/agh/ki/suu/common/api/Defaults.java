package pl.edu.agh.ki.suu.common.api;

public interface Defaults {
    int PORT = 27017;
    String HOST = "localhost";
    String DB_NAME = "queues";
    String COLLECTION_NAME = "queue";
    int MONGO_CHECKIN_INTERVAL = 1000;
}
