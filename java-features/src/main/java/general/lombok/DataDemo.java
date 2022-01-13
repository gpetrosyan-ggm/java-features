package general.lombok;

import lombok.*;

@Data(staticConstructor = "create")
public class DataDemo {

    @Setter(value = AccessLevel.NONE)
    private Long id;

    @Getter(value = AccessLevel.NONE)
    private String username;

    @ToString.Exclude
    private boolean active;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private int role;

}
