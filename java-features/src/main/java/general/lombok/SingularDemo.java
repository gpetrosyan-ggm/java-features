package general.lombok;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Builder(builderClassName = "Builder")
@Getter
@ToString
public class SingularDemo {

    private Long id;

    @Singular(ignoreNullCollections = true, value = "singleAircraft")
    private List<String> aircraft;

    @Singular
    private Map<Integer, String> students;

}
