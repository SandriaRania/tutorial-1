package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Car;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class CarDelete {
    public void deleteCar(String id, List<Car> carData) {
        carData.removeIf(car -> car.getCarId().equals(id));
    }
}