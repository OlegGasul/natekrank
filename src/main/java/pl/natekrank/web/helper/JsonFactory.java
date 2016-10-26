package pl.natekrank.web.helper;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class JsonFactory {

    public String generateJson(Object object) {
        ObjectMapper jsonMapper = new ObjectMapper();
        String json = "{}";
        try {
            json = jsonMapper.writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }

}
