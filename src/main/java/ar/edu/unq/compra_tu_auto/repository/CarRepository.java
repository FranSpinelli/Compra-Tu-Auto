package ar.edu.unq.compra_tu_auto.repository;

import ar.edu.unq.compra_tu_auto.model.Car;

import java.util.Optional;

public interface CarRepository {

    Car saveCar(Car carToBeCreated);

    Optional<Car> getCarWithId(Integer carId);

    void deleteCar(Integer carId);
}
