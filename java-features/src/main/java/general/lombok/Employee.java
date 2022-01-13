package general.lombok;

import lombok.*;
import lombok.experimental.NonFinal;

import javax.management.ConstructorParameters;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor //(onConstructor_ = @ConstructorParameters({"id"}))
public class Employee implements Serializable {

    private Long id;

    //@Getter(onMethod=@__(@Deprecated)) -- JDK7
    @Getter(onMethod_ = @Deprecated) // JDK8
    @Setter(onParam_ = @NonFinal)
    private String email;

    @Getter(onMethod_ = {
            @Generated,
            @Deprecated
    })
    private boolean active;

}
