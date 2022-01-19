package general.jackson.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Zoo {

    private Animal animal;

    @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            include = JsonTypeInfo.As.PROPERTY,
            property = "type"
    )
    @JsonSubTypes({
            @JsonSubTypes.Type(value = Dog.class, name = "dog"),
            @JsonSubTypes.Type(value = Cat.class, name = "cat")
    })
    @Setter
    @Getter
    @ToString
    public static class Animal {
        private String name;
    }

    @JsonTypeName("dog")
    @Setter
    @Getter
    @ToString
    public static class Dog extends Animal {
        private double barkVolume;
    }

    @JsonTypeName("cat")
    @Setter
    @Getter
    @ToString
    public static class Cat extends Animal {
        private boolean likesCream;
        private int lives;
    }

}
