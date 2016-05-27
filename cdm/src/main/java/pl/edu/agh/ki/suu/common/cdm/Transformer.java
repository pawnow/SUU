package pl.edu.agh.ki.suu.common.cdm;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.io.StringWriter;

/**
 * Created by Ogoor on 2016-05-08.
 */
public class Transformer {

    Unmarshaller messageUnmarshaller;
    Marshaller messageMarshaller;
    Unmarshaller confUnmarshaller;
    Marshaller confMarshaller;

    public Transformer() {
        try {
            messageUnmarshaller = JAXBContext.newInstance(Message.class).createUnmarshaller();
            messageMarshaller = JAXBContext.newInstance(Message.class).createMarshaller();
            confUnmarshaller = JAXBContext.newInstance(Configuration.class).createUnmarshaller();
            confMarshaller = JAXBContext.newInstance(Configuration.class).createMarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public String marshal(Message message) throws JAXBException {
        StringWriter sw = new StringWriter();
        messageMarshaller.marshal(message, sw);
        return sw.toString();
    }

    public Message unmarshalMessage(InputStream message) throws JAXBException {
        return (Message) messageUnmarshaller.unmarshal(message);
    }

    public String marshal(Configuration conf) throws JAXBException {
        StringWriter sw = new StringWriter();
        confMarshaller.marshal(conf, sw);
        return sw.toString();
    }

    public Configuration unmarshalConfiguration(InputStream configuration) throws JAXBException {
        return (Configuration) confUnmarshaller.unmarshal(configuration);
    }

}
