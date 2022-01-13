package general.gson;

import com.google.gson.InstanceCreator;

import java.lang.reflect.Type;

public class CarCreator implements InstanceCreator<Car> {

    @Override
    public Car createInstance(Type type) {
        Car car = new Car();
        car.setBrand("Toyota");
        return car;
    }

}
