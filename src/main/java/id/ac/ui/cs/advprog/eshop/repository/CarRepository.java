package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Car;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class CarRepository {
    private List<Car> carData = new ArrayList<>();
    CarCreate create = new CarCreate();
    CarFindAll findAll = new CarFindAll();
    CarFindbyId findById = new CarFindbyId();
    CarUpdate update = new CarUpdate();
    CarDelete delete = new CarDelete();

    public Car create(Car car) {
        return create.createCar(car, carData);
    }
    public Iterator<Car> findAll(){
        return findAll.findAllCar(carData);
    }
    public Car findById(String id) {
        return findById.findByIdCar(id, carData);
    }
    public Car update(String id, Car updatedCar) {
        return update.updateCar(id, updatedCar, carData);
    }
    public void delete(String id) {
        delete.deleteCar(id, carData);
    }
}