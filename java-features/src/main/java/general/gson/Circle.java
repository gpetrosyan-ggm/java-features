package general.gson;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Circle {

    private double radius;

    public double getArea() {
        return (radius * radius * 3.14);
    }

}
