package general.lombok;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
public class NotNullDemo {

    private Long id;

    @NonNull
    private String name;

    @NonNull
    private Boolean active;

    @NonNull
    private int role;

    public NotNullDemo(@NonNull Long id, @NonNull String name) {
        this.id = id;
        this.name = name;
    }

    public Double getSurcharge(@NonNull Double transactionAmt) {
        return Math.PI;
    }

}
