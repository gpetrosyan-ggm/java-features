package general.jackson;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class JsonGeneratorDemo {

    public static void main(String[] args) throws IOException {
        JsonFactory factory = new JsonFactory();

        simpleDemo(factory);
    }

    private static void simpleDemo(JsonFactory factory) throws IOException {
        final String filePath = "java-features/src/main/resources/generator-output.json";
        JsonGenerator generator = factory.createGenerator(new File(filePath), JsonEncoding.UTF8);

        generator.writeStartObject();
        generator.writeStringField("brand", "Mercedes");
        generator.writeNumberField("doors", 5);
        generator.writeFieldName("specs");
        generator.writeStartArray();
        generator.writeNumber(11);
        generator.writeNumber(22);
        generator.writeNumber(33);
        generator.writeEndArray();
        generator.writeEndObject();

        generator.close();

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> dataMap = mapper.readValue(new File(filePath), Map.class);
        System.out.println(dataMap);
    }

}
