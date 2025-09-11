package ar.edu.unq.compra_tu_auto.repository;

import ar.edu.unq.compra_tu_auto.model.Car;

import java.util.Optional;

public interface CarRepository {

    Car saveCar(Car carToBeCreated);

    Optional<Car> getCarByIdAndDealershipId(Integer carId, Integer dealershipId);

    void deleteCar(Integer carId, Integer dealershipId);
}
