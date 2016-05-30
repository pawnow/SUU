package pl.edu.agh.ki.suu.client.unregister;

/**
 * Created by mariuszz on 2016-05-30.
 */
public class JMSUnregisterClient {
    public static void main(String[] args) {
        ClientUnregister.unregister("tcp://localhost:56789", "messages-jms");
    }
}
