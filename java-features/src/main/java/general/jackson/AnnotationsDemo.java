package general.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import general.jackson.model.Person;
import general.jackson.model.PersonImmutable;

import java.io.IOException;

public class AnnotationsDemo {

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);

        InjectableValues inject = new InjectableValues.Std().addValue(String.class, "demo.com");

        Person person = new Person();
        person.setId(1L);
        person.setFullName("John Travolta");
        person.setFirstName("John");
        person.setLastName("Travolta");
        person.setIsAdmin(true);

        String personJson = objectMapper.writeValueAsString(person);
        System.out.println(personJson);


        Person personMapped = objectMapper.readerFor(Person.class)
                                          .forType(Person.class)
                                          .readValue(personJson, Person.class);
        System.out.println(personMapped);


        PersonImmutable personImmutable = new PersonImmutable(1, "Thom");

        String personImmutableJson = objectMapper.writeValueAsString(personImmutable);
        System.out.println(personImmutableJson);

        PersonImmutable personImmutableMapped = objectMapper.readValue(personImmutableJson, PersonImmutable.class);
        System.out.println(personImmutableMapped);
    }

}
