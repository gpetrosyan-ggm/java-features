package general.lombok;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.NonFinal;

@NoArgsConstructor(force = true, staticName = "INSTANCE", onConstructor_= {@Deprecated})
public class NoArgsDemo {

    @NonNull
    private final Long id;

    private Long idNot;

    private final String username;

    private final double minSalary;

    private final int defaultRole = 1;

    private static boolean status;

}
