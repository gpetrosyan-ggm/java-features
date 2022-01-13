package general.lombok;

import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.time.LocalDate;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, doNotUseGetters = true, onParam_ = {@NonNull})
//@EqualsAndHashCode(of = {"id"})
public class EqualsHashCodeDemo {

    @EqualsAndHashCode.Include
    private Long id;

    private static int defaultRole = 1;

    private LocalDate dob;

    private transient String dobString;

}
