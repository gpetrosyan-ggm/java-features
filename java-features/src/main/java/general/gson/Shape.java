package general.gson;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Shape<T> {

    public T shape;

    public double getArea() {
        if (shape instanceof Circle) {
            return ((Circle) shape).getArea();
        } else {
            return 0.0;
        }
    }

}
