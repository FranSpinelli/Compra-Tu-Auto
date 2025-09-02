package ar.edu.unq.compra_tu_auto.service;


import ar.edu.unq.compra_tu_auto.controller.DTO.CarDTO;
import ar.edu.unq.compra_tu_auto.model.Car;

import java.util.Optional;

public interface CarService {

    Car createCar(CarDTO carDTO);

    Car updateCar(Integer carId, CarDTO carDTO);

    Optional<Car> getCarWithId(Integer carId);

    void deleteCar(Integer carId);
}
