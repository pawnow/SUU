package pl.edu.agh.ki.suu.client.unregister;

/**
 * Created by mariuszz on 2016-05-30.
 */
public class RESTUnregisterClient {

    public static void main(String[] args) {
        ClientUnregister.unregister("http://localhost:7070/rest", "messages-rest");
    }
}
