package general.gson;

import com.google.gson.annotations.Expose;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Car {

    @Expose(serialize = true, deserialize = true)
    private String brand;

    @Expose(serialize = true, deserialize = true)
    private Integer doors;

    @Expose(serialize = true, deserialize = true)
    private String name;

}
