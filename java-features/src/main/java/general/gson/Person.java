package general.gson;

import com.google.gson.annotations.Since;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Person {

    @Since(1.0)
    private String firstName;

    @Since(1.0)
    private String lastName;

    @Since(2.0)
    private String middleName;

    @Since(3.0)
    private String email;

    @Since(2.0)
    private Boolean isAdmin;

}
