package general.gson;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.*;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class GsonDemo {

    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        Gson gsonBuilder = new GsonBuilder()
                .setPrettyPrinting()
                .excludeFieldsWithModifiers(Modifier.TRANSIENT)
//                .excludeFieldsWithoutExposeAnnotation()
                .serializeNulls()
                .registerTypeAdapter(Car.class, new CarCreator())
                .registerTypeAdapter(Boolean.class, new BooleanDeserializer())
                .registerTypeAdapter(Boolean.class, new BooleanSerializer())
                .registerTypeAdapter(Student.class, new StudentAdapter())
//                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DOTS)
                .setVersion(2.0)
                .create();

//        parseToCar(gsonBuilder);
//        parseFromCar(gsonBuilder);
//        parseFromPerson(gsonBuilder);
//        parseToPerson(gsonBuilder);
//        genericDemo(gsonBuilder);
//        readerDemo();
//        parserDemo();
//        customAdapterDemo(gsonBuilder);
        webUrlDemo(gsonBuilder);
    }

    public static void webUrlDemo(Gson gson) throws IOException {
        String webPage = "http://time.jsontest.com";
        try (InputStream is = new URL(webPage).openStream();
             Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {

            TimeData td = gson.fromJson(reader, TimeData.class);
            System.out.println(td);
        }
    }

    public static void customAdapterDemo(Gson gson) {
        String jsonString = "{\"name\":\"Mahesh\", \"rollNo\":1}";
        Student student = gson.fromJson(jsonString, Student.class);
        System.out.println(student);

        jsonString = gson.toJson(student);
        System.out.println(jsonString);
    }

    public static void genericDemo(Gson gson) {
        Circle circle = new Circle(5.0);

        Shape<Circle> shape = new Shape<>();
        shape.setShape(circle);

        // Define a Type shapeType of type circle.
        Type shapeType = new TypeToken<Shape<Circle>>() {
        }.getType();

        //Serialize the json as ShapeType
        String jsonString = gson.toJson(shape, shapeType);
        System.out.println(jsonString);

        Shape shape1 = gson.fromJson(jsonString, Shape.class);
        System.out.println(shape1.getClass());
        System.out.println(shape1.toString());
        System.out.println(shape1.getArea());

        Shape shape2 = gson.fromJson(jsonString, shapeType);
        System.out.println(shape2.getClass());
        System.out.println(shape2.toString());
        System.out.println(shape2.getArea());
    }

    public static void parserDemo() {
        String json = "{ \"f1\":\"Hello\",\"f2\":{\"f3\":\"World\"}}";
        JsonElement jsonTree = JsonParser.parseString(json);

        if (jsonTree.isJsonObject()) {
            JsonObject jsonObject = jsonTree.getAsJsonObject();

            JsonElement f1 = jsonObject.get("f1");
            System.out.println(f1);

            JsonElement f2 = jsonObject.get("f2");
            System.out.println(f2);

            if (f2.isJsonObject()) {
                JsonObject f2Obj = f2.getAsJsonObject();

                JsonElement f3 = f2Obj.get("f3");
                System.out.println(f3);
            }
        }
    }

    public static void readerDemo() {
        String json = "{\"brand\" : \"Toyota\", \"doors\" : 5}";
        JsonReader jsonReader = new JsonReader(new StringReader(json));
        printJsonReader(jsonReader);
    }

    public static void parseToCar(Gson gson) {
        String json = "{\"brand\":\"Jeep\", \"doors\": 3, \"name\": \"Wrangler\"}";
        Car car = gson.fromJson(json, Car.class);
        System.out.println(car);
    }

    public static void parseFromCar(Gson gson) {
        Car car = new Car("BMW", 5, "X5");
        String json = gson.toJson(car);
        System.out.println(json);
    }

    public static void parseFromPerson(Gson gson) {
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setMiddleName("Blocks");
        person.setEmail("john@doe.com");
        person.setIsAdmin(true);

        String personJson = gson.toJson(person);
        System.out.println(personJson);
    }

    public static void parseToPerson(Gson gson) {
        String json =
                "{\"firstName\":\"John\",\"lastName\":\"Doe\",\"middleName\":\"Blocks\",\"email\":\"john@doe.com\",\"isAdmin\":\"0\"}";
        Person person = gson.fromJson(json, Person.class);
        System.out.println(person);
    }

    public static void printJsonReader(JsonReader jsonReader) {
        try {
            while (jsonReader.hasNext()) {
                JsonToken nextToken = jsonReader.peek();
                System.out.println(nextToken);

                if (JsonToken.BEGIN_OBJECT.equals(nextToken)) {
                    jsonReader.beginObject();
                } else if (JsonToken.END_OBJECT.equals(nextToken)) {
                    jsonReader.endObject();
                } else if (JsonToken.NAME.equals(nextToken)) {
                    String name = jsonReader.nextName();
                    System.out.println(name);
                } else if (JsonToken.STRING.equals(nextToken)) {
                    String value = jsonReader.nextString();
                    System.out.println(value);
                } else if (JsonToken.NUMBER.equals(nextToken)) {
                    long value = jsonReader.nextLong();
                    System.out.println(value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
