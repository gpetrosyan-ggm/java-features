package general.jackson;

import com.fasterxml.jackson.core.*;

import java.io.File;
import java.io.IOException;

public class JsonGeneratorDemo {

    public static void main(String[] args) throws IOException {
        JsonFactory factory = new JsonFactory();

        simpleDemo(factory);
    }

    // TODO fix
    private static void simpleDemo(JsonFactory factory) throws IOException {
        JsonGenerator generator = factory.createGenerator(
                new File("generator-output.json"),
                JsonEncoding.UTF8);

        generator.writeStartObject();
        generator.writeStringField("brand", "Mercedes");
        generator.writeNumberField("doors", 5);
        generator.writeStartArray("specs");
        generator.writeStartArray();
        generator.writeString("aaa");
        generator.writeString("bbb");
        generator.writeString("ccc");
        generator.writeEndArray();
        generator.writeEndObject();

        generator.close();
    }

}
