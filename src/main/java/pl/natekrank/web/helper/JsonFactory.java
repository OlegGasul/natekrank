package pl.natekrank.web.helper;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class JsonFactory {

    public String generateJson(Object object) {
        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        String json = "{}";
        try {
            json = jsonMapper.writeValueAsString(object);
            json = escape(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }

    private String escape(String json) {
        return json.replaceAll("\"", "\\\"").replaceAll("\'", "&rsquo;");
    }
}
