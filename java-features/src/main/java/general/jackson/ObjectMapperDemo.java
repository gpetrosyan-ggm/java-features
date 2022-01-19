package general.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import general.jackson.model.Car;
import general.jackson.serializer.CarDeserializer;
import general.jackson.serializer.CarSerializer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Map;

public class ObjectMapperDemo {

    public static void main(String[] args) throws JsonProcessingException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        objectMapper.setDateFormat(df);

        jsonNodeTreeFieldDemo(objectMapper);
    }

    private static void objectNodeDemo(ObjectMapper objectMapper) throws JsonProcessingException {
        ObjectNode parentNode = objectMapper.createObjectNode();

        String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";
        JsonNode carJsonNode = objectMapper.readTree(carJson);

        parentNode.set("vehicle", carJsonNode);
        parentNode.put("human", true);
        parentNode.put("area", "USA");

        String parentNodeJson = objectMapper.writeValueAsString(parentNode);
        System.out.println(parentNodeJson);

        parentNode.remove("human");
        parentNodeJson = objectMapper.writeValueAsString(parentNode);
        System.out.println(parentNodeJson);
    }

    private static void jsonNodeTreeFieldDemo(ObjectMapper objectMapper) throws JsonProcessingException {
        String carJson =
                "{  \"mark\" : null, \"brand\" : \"Mercedes\", \"doors\" : 5, \"spec\" : { \"sport\" : true, \"color\": \"red\" }}";
        JsonNode carJsonNode = objectMapper.readTree(carJson);
        JsonNode brandField = carJsonNode.get("brand");
        System.out.println("Brand: " + brandField.asText());

        JsonNode markField = carJsonNode.get("mark");
        if (markField != null) {
            System.out.println("Mark: " + markField.asText("Not identified"));
        }

        JsonNode sportField = carJsonNode.at("/spec/sport");
        System.out.println("Sport: " + sportField.asBoolean());

        JsonNode unknownField = carJsonNode.at("/spec/sport/aaa");
        System.out.println("Unknown: " + unknownField.asText("Didn't find"));

        System.out.println("Traverse DEMO: ");
        System.out.println();
        traverse(carJsonNode);
    }

    private static void traverse(JsonNode jsonNode) {
        if (jsonNode.isObject()) {

            // Field names
            // Iterator<String> fieldNames = jsonNode.fieldNames();

            // Fields (name : value)
            Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();

            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                String fieldName = field.getKey();
                System.out.print(fieldName + " : ");
                JsonNode fieldValue = field.getValue();
                traverse(fieldValue);
            }
        } else if (jsonNode.isArray()) {
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            for (int i = 0; i < arrayNode.size(); i++) {
                JsonNode arrayElement = arrayNode.get(i);
                traverse(arrayElement);
            }
        } else {
            // JsonNode root represents a single value field - do something with it.
            System.out.println(jsonNode.asText());
        }
    }

    private static void jsonNodeTreeToValueDemo(ObjectMapper objectMapper) throws JsonProcessingException {
        String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";
        JsonNode carJsonNode = objectMapper.readTree(carJson);

        Car car = objectMapper.treeToValue(carJsonNode, Car.class);
        System.out.println(car);
    }

    private static void jsonNodeValueToTreeDemo(ObjectMapper objectMapper) throws JsonProcessingException {
        Car car = new Car();
        car.setBrand("Cadillac");
        car.setDoors(4);

        JsonNode carJsonNode = objectMapper.valueToTree(car);
    }

    private static void jsonNodeDemo(ObjectMapper objectMapper) throws JsonProcessingException {
        String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";
        JsonNode jsonNode = objectMapper.readTree(carJson);

        JsonNode brandNode = jsonNode.get("brand");
        System.out.println("brand = " + brandNode.asText());

        JsonNode doorsNode = jsonNode.get("doors");
        System.out.println("doors = " + doorsNode.asInt());

//        JsonNode array = jsonNode.get("owners");
//        jsonNode = array.get(0);
//        String john = jsonNode.asText();
//        System.out.println("john  = " + john);

//        JsonNode child = jsonNode.get("nestedObject");
//        JsonNode childField = child.get("field");
//        String field = childField.asText();
//        System.out.println("field = " + field);

        Car car = new Car();
        car.setBrand("Cadillac");
        car.setDoors(4);

        JsonNode carJsonNode = objectMapper.valueToTree(car);
    }

    private static void customSerializerDemo(ObjectMapper objectMapper) throws JsonProcessingException {
        SimpleModule module = new SimpleModule("CarSerializer", new Version(2, 1, 3, null, null, null));
        module.addSerializer(Car.class, new CarSerializer());
        objectMapper.registerModule(module);

        Car car = new Car();
        car.setBrand("Mercedes");
        car.setDoors(5);
        car.setColor("Black");

        String carJson = objectMapper.writeValueAsString(car);
        System.out.println(carJson);
    }

    private static void customDeserializerDemo(ObjectMapper objectMapper) throws JsonProcessingException {
        String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : null, \"color\" : \"red\", \"speed\" : 100 }";
        SimpleModule module = new SimpleModule("CarDeserializer", new Version(3, 1, 8, null, null, null));
        module.addDeserializer(Car.class, new CarDeserializer());
        objectMapper.registerModule(module);

        Car car = objectMapper.readValue(carJson, Car.class);
        System.out.println(car);
    }

    private static void simpleDemo(ObjectMapper objectMapper) throws JsonProcessingException {
        String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : null, \"color\" : \"red\", \"speed\" : 100 }";
        System.out.println(carJson);

        Car car = objectMapper.readValue(carJson, Car.class);
        System.out.println(car);
    }

}
