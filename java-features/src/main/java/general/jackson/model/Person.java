package general.jackson.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import general.jackson.serializer.OptimizedBooleanDeserializer;
import general.jackson.serializer.OptimizedBooleanSerializer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties({"firstName"})
@JsonPropertyOrder({"fullName", "lastName"})
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NON_PRIVATE)
//@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Person {

    @JsonIgnore
    private Long id;

    @JsonSetter("full-name")
    private String fullName;

    private String firstName;

    private String lastName;

    //    @JacksonInject
    public String source = null;

    @JsonDeserialize(using = OptimizedBooleanDeserializer.class)
    @JsonSerialize(using = OptimizedBooleanSerializer.class)
    private Boolean isAdmin;

    @JsonRawValue
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String address = "{ \"street\" : \"Wall Street\", \"no\":1}";

    // all Address instances will be ignored
    @JsonIgnoreType
    public static class Address {
        public String streetName;
        public String houseNumber;
        public String zipCode;
        public String city;
        public String country;
    }

}
