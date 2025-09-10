package ar.edu.unq.compra_tu_auto.service;


import ar.edu.unq.compra_tu_auto.controller.DTO.car.CarRequestDTO;
import ar.edu.unq.compra_tu_auto.model.Car;

import java.util.Optional;

public interface CarService {

    Car createCar(Integer dealershipId, CarRequestDTO carRequestDTO);

    Car updateCar(Integer dealershipId, Integer carId, CarRequestDTO carRequestDTO);

    Optional<Car> getCarWithId(Integer dealershipId, Integer carId);

    void deleteCar(Integer dealershipId, Integer carId);
}
