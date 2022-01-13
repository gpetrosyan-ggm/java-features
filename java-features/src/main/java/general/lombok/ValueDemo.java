package general.lombok;

import lombok.*;
import lombok.experimental.NonFinal;

@Value
@Builder
public class ValueDemo {

    @NonFinal
    Long id;

    @Getter(value = AccessLevel.NONE)
    String username;

    @ToString.Exclude
    String name;

    @EqualsAndHashCode.Include
    private static String pwd;

}
