package general.jackson;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.IOException;

public class JsonParserDemo {

    public static void main(String[] args) throws IOException {
        JsonFactory factory = new JsonFactory();

        simpleDemo(factory);
    }

    private static void simpleDemo(JsonFactory factory) throws IOException {
        String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";
        JsonParser parser = factory.createParser(carJson);

        while (!parser.isClosed()) {
            JsonToken jsonToken = parser.nextToken();

            if (JsonToken.FIELD_NAME.equals(jsonToken)) {
                String fieldName = parser.getCurrentName();
                System.out.println(fieldName);

                jsonToken = parser.nextToken();

                if ("brand".equals(fieldName)) {
                    System.out.println(parser.getValueAsString());
                } else if ("doors".equals(fieldName)) {
                    System.out.println(parser.getValueAsInt());
                }
            }
        }
    }

}
