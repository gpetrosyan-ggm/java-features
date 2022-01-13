package general.lombok;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
//@Accessors(chain = false, fluent = true)
@Accessors(prefix = "f")
public class AccessorsDemo {

    @Accessors(chain = true)
    private Long id;

    @Accessors(prefix = "is_")
    private String name;

}
