package pl.edu.agh.ki.suu.common.cdm;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

/**
 * Created by Ogoor on 2016-05-08.
 */
public class TransformerTest {

    private Transformer transformer = new Transformer();

    private final String MESSAGE_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
            "<Message>" +
            "<ProtocolVersion>latest protocol version</ProtocolVersion>" +
            "<Sender>" +
            "<Name>client1</Name>" +
            "<Address>ulica sezamkowa 1</Address>" +
            "</Sender>" +
            "<Target>" +
            "<Name>client2</Name>" +
            "<Address>ulica sezamkowa 2</Address>" +
            "</Target>" +
            "<Timestamp>2016-30-05-20:20:05</Timestamp>" +
            "<Timeout>500000</Timeout>" +
            "<Payload>My message!</Payload>" +
            "</Message>";

    private final String CONF_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
            "<Configuration>" +
            "<ProtocolVersion>not latest protocol version</ProtocolVersion>" +
            "<Sender>" +
            "<Name>clien 3</Name>" +
            "<Address>wadowicka 6</Address>" +
            "</Sender>" +
            "<SomeParameter>very important parameter</SomeParameter>" +
            "</Configuration>";

    @Test
    public void messageMarshalUnmarshalTest() throws Exception {
        //given
        InputStream is = string2inputSteam(MESSAGE_XML);

        //when
        Message messageObj = transformer.unmarshalMessage(is);
        String result = transformer.marshal(messageObj);

        //then
        assertEquals(result, MESSAGE_XML);
    }

    @Test
    public void confMarshalUnmarshalTest() throws Exception {
        //given
        InputStream is = string2inputSteam(CONF_XML);

        //when
        Configuration confObj = transformer.unmarshalConfiguration(is);
        String result = transformer.marshal(confObj);

        //then
        assertEquals(result, CONF_XML);
    }

    private InputStream string2inputSteam(String str) {
        return new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8));
    }


}
