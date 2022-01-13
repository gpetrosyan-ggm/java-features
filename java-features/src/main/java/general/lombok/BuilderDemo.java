package general.lombok;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(builderClassName = "Builder")
public class BuilderDemo {

    private Long id;

    @lombok.Builder.Default
    private String name = "test";

    private boolean status;

    private int role = 1;

}
