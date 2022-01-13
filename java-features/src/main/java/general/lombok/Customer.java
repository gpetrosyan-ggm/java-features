package general.lombok;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Setter
public class Customer implements Serializable {

    private Long id;

    private String firstName;

    @Setter(AccessLevel.PACKAGE)
    private String lastName;

    @Setter(AccessLevel.NONE)
    private int age;

    private boolean admin;

    private boolean isAdmin;

    private Boolean citizenOfUS;

    // returns this for setter
    @Accessors(fluent = true)
    private String email;

    // user created setter
    public Customer setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @Getter
    private String status = simpleDefaultStatus();

    @Getter(lazy = true)
    private final double surCharge = complexSurchargeLogic();

    private double complexSurchargeLogic() {

        double surcharge = Math.PI;
        System.out.println("Called Lazy getSurcharge method");
        return surcharge;
    }

    private String simpleDefaultStatus() {
        System.out.println("Called EAGER getStatus method");
        return "NOT_INITIALIZED";
    }

}
