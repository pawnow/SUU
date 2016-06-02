package pl.edu.agh.ki.suu.client.unregister;

/**
 * Created by mariuszz on 2016-05-30.
 */
public class WSUnregisterClient {

    public static void main(String[] args) {
        ClientUnregister.unregister("http://localhost:9090/ws", "messages-ws");
    }
}
