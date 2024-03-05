package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Car;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class CarRepository {
<<<<<<< HEAD
    static int id = 0;
    private List<Car> carData = new ArrayList<>();
    public Car create(Car car) {
        if(car.getCarId() == null) {
            UUID uuid = UUID.randomUUID();
            car.setCarId(uuid.toString());
        }
        carData.add(car);
        return car;
    }
    public Iterator<Car> findAll(){
        return carData.iterator();
    }
    public Car findById(String id) {
        for (Car car : carData) {
            if (car.getCarId().equals(id)) {
                return car;
            }
        }
        return null;
    }
    public Car update(String id, Car updatedCar) {
        for (int i = 0; i < carData.size(); i++) {
            Car car = carData.get(i);
            if (car.getCarId().equals(id)) {
                car.setCarName(updatedCar.getCarName());
                car.setCarColor(updatedCar.getCarColor());
                car.setCarQuantity(updatedCar.getCarQuantity());
                return car;
            }
        }
        return null;
    }
    public void delete(String id) {
        carData.removeIf(car -> car.getCarId().equals(id));
    }
}
=======
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
>>>>>>> origin/main
