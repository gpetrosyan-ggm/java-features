package general.lombok;

import lombok.ToString;

//@ToString(includeFieldNames = false, exclude = {"name"})
@ToString(onlyExplicitlyIncluded = true, doNotUseGetters = false)
public class ToStringDemo {

    @ToString.Include(name = "userId", rank = 2)
    private Long id;

    private String name;

    @ToString.Include(name = "status", rank = 2)
    private static boolean defaultStatus = false;

}
