package general.lombok;

import lombok.*;

@Value
public class LoginBase {

    @With
    Integer id;

    String age;

}
