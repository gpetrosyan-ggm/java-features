package general.lombok;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor(
        access = AccessLevel.PRIVATE,
        staticName = "getInstance",
        onConstructor_=@Deprecated)
public class AllArgsDemo {

    private final Long id;

    @NonNull
    private Long idNot;

    private final String username;

    private final double minSalary;

    private final int defaultRole = 1;

    private static boolean status;

}
