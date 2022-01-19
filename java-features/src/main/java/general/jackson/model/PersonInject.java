package general.jackson.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PersonInject {

    @JacksonInject("personId")
    private Integer id;

    private String name;

}
