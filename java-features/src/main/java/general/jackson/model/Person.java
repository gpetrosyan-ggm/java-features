package general.jackson.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import general.jackson.serializer.OptimizedBooleanDeserializer;
import general.jackson.serializer.OptimizedBooleanSerializer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
@JsonIgnoreProperties({"firstName"})
@JsonPropertyOrder({"fullName", "lastName"})
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NON_PRIVATE)
//@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonRootName(value = "PERSON-INFO", namespace = "PERSONS")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Person {

    @JsonIgnore
    private Long id;

    private String fakeName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    public Date eventDate;

    @JsonSetter("full-name")
    private String fullName;

    private String firstName;

    @JsonAlias({"lName", "l_name"})
    private String lastName;

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

    private Map<String, String> properties = new HashMap<>();

    @JsonAnyGetter
    public Map<String, String> getProperties() {
        return properties;
    }

    public void add(String property, String value) {
        properties.put(property, value);
    }

}
