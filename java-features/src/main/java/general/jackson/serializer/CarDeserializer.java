package general.jackson.serializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import general.jackson.model.Car;

import java.io.IOException;

public class CarDeserializer extends JsonDeserializer<Car> {

    @Override
    public Car deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JacksonException {
        Car car = new Car();
        while (!jsonParser.isClosed()) {
            JsonToken jsonToken = jsonParser.nextToken();

            if (JsonToken.FIELD_NAME.equals(jsonToken)) {
                String fieldName = jsonParser.getCurrentName();
                System.out.println(fieldName);

                jsonToken = jsonParser.nextToken();

                if ("brand".equals(fieldName)) {
                    car.setBrand(jsonParser.getValueAsString());
                } else if ("doors".equals(fieldName)) {
                    car.setDoors(jsonParser.getValueAsInt());
                }
            }
        }
        return car;
    }

}
