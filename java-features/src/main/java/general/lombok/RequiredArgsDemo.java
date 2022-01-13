package general.lombok;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(
        access = AccessLevel.PRIVATE,
        staticName = "instance",
        onConstructor_= {@Deprecated})
public class RequiredArgsDemo {

    private Long id;

    private static Long age;

    @NonNull
    private String username;

    @NonNull
    private String name = "";

    @NonNull
    private String email;

    private final boolean status;

    private final int defaultRole = 1;

}
