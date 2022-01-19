package general.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import general.jackson.model.*;
import general.lombok.User;

import java.io.IOException;
import java.util.Date;

public class AnnotationsDemo {

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        objectMapper.writer(new DefaultPrettyPrinter());

        Person person = new Person();
        person.setId(1L);
        person.setFullName("John Travolta");
        person.setFakeName(null);
        person.setFirstName("John");
        person.setLastName("Travolta");
        person.setIsAdmin(true);
        person.setEventDate(new Date());

        person.add("Name", "Mark");
        person.add("RollNo", "1");

        String personJson = objectMapper.writeValueAsString(person);
        System.out.println(personJson);

        Person personMapped = objectMapper.readValue(personJson, Person.class);
        System.out.println(personMapped);

        String personAliasJson = "{\"full-name\":\"John Travolta\", \"lName\":\"Bond111\", \"l_name\":\"Bond\"}";
        Person personAliasMapped = objectMapper.readValue(personAliasJson, Person.class);
        System.out.println(personAliasMapped);

        String personInjectJson = "{\"name\":\"My bean\"}";
        InjectableValues inject = new InjectableValues.Std().addValue("personId", 1);
        ObjectMapper mapped = new ObjectMapper();
        mapped.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapped.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        mapped.setInjectableValues(inject);
        PersonInject personInjectMapped = mapped.readValue(personInjectJson, PersonInject.class);
        System.out.println(personInjectMapped);

        PersonImmutable personImmutable = new PersonImmutable(1, "Thom");

        String personImmutableJson = objectMapper.writeValueAsString(personImmutable);
        System.out.println(personImmutableJson);

        PersonImmutable personImmutableMapped = objectMapper.readValue(personImmutableJson, PersonImmutable.class);
        System.out.println(personImmutableMapped);

        typeAnnotationsDemo(objectMapper);
        generalAnnotationsDemo(objectMapper);
    }

    private static void typeAnnotationsDemo(ObjectMapper objectMapper) throws JsonProcessingException {
        Zoo.Dog dog = new Zoo.Dog();
        dog.setName("lacy");
        dog.setBarkVolume(10);

        Zoo zooDog = new Zoo();
        zooDog.setAnimal(dog);

        Zoo.Cat cat = new Zoo.Cat();
        cat.setName("mau");
        cat.setLives(10);
        cat.setLikesCream(true);

        Zoo zooCat = new Zoo();
        zooCat.setAnimal(cat);

        String dogJson = objectMapper.writeValueAsString(zooDog);
        String catJson = objectMapper.writeValueAsString(zooCat);

        System.out.println(dogJson);
        System.out.println(catJson);
    }

    private static void generalAnnotationsDemo(ObjectMapper objectMapper) throws JsonProcessingException {
        GeneralUser.Name name = new GeneralUser.Name("John", "Doe");
        GeneralUser user = new GeneralUser(1, name, 5, "itemTes", "itemOwnerTest");

        String userJson = objectMapper
                .writerWithView(Views.Public.class)
                .writeValueAsString(user);
        System.out.println(userJson);

        UserWithRef userRef = new UserWithRef(1, "John");
        ItemWithRef itemRef = new ItemWithRef(2, "book", userRef);
        userRef.addItem(itemRef);

        String itemRefJson = objectMapper.writeValueAsString(itemRef);
        System.out.println(itemRefJson);

        String userRefJson = objectMapper.writeValueAsString(userRef);
        System.out.println(userRefJson);

        UserWithIdentity userWithIdentity = new UserWithIdentity(1, "John");
        ItemWithIdentity itemWithIdentity = new ItemWithIdentity(2, "book", userWithIdentity);
        userWithIdentity.addItem(itemWithIdentity);

        String userWithIdentityJson = objectMapper.writeValueAsString(userWithIdentity);
        System.out.println(userWithIdentityJson);

        String itemWithIdentityJson = objectMapper.writeValueAsString(itemWithIdentity);
        System.out.println(itemWithIdentityJson);


        BeanWithFilter beanFilter = new BeanWithFilter(1, "My bean");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("myFilter", SimpleBeanPropertyFilter.filterOutAllExcept("name"));

        String beanFilterJson = objectMapper.writer(filters).writeValueAsString(beanFilter);
        System.out.println(beanFilterJson);

        BeanWithCustomAnnotation beanWithCustomAnnotation = new BeanWithCustomAnnotation(1, "My bean", null);
        String beanWithCustomAnnotationJson = objectMapper.writeValueAsString(beanWithCustomAnnotation);
        System.out.println(beanWithCustomAnnotationJson);

        Item item = new Item(1, "book", null);
        String itemJson = objectMapper.writeValueAsString(item);
        System.out.println(itemJson);

        ObjectMapper mapper = new ObjectMapper();
        mapper.addMixIn(User.class, MyMixInForIgnoreType.class);
        String itemMixInJson = mapper.writeValueAsString(item);
        System.out.println(itemMixInJson);
    }

}
