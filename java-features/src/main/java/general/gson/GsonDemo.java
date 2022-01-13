package general.gson;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.IOException;
import java.io.StringReader;

public class GsonDemo {

    public static void main(String[] args) {
        Gson gson = new Gson();
        Gson gsonBuilder = new GsonBuilder()
                .setPrettyPrinting()
//                .excludeFieldsWithoutExposeAnnotation()
                .serializeNulls()
                .registerTypeAdapter(Car.class, new CarCreator())
                .registerTypeAdapter(Boolean.class, new BooleanDeserializer())
                .registerTypeAdapter(Boolean.class, new BooleanSerializer())
                .setVersion(2.0)
                .create();

//        parseToCar(gsonBuilder);
//        parseFromCar(gsonBuilder);
//        parseFromPerson(gsonBuilder);
//        parseToPerson(gsonBuilder);
        readerDemo();
        parserDemo();
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
