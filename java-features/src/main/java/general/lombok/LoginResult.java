package general.lombok;

import lombok.*;
import lombok.experimental.Accessors;

import java.net.URL;
import java.time.Duration;
import java.time.Instant;

@RequiredArgsConstructor
@Accessors(fluent = true)
@Getter
@ToString(callSuper = true, exclude = {"loginTs"})
@EqualsAndHashCode(of = {"authToken"})
public class LoginResult {

    @NonNull
    private final Instant loginTs;

    @NonNull
    private final String authToken;

    @NonNull
    private final Duration tokenValidity;

    @NonNull
    private final URL tokenRefreshUrl;

    private final String userName;

}
