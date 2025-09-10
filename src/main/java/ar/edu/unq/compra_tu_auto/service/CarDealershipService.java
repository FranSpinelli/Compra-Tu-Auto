package ar.edu.unq.compra_tu_auto.service;

import ar.edu.unq.compra_tu_auto.controller.DTO.carDealership.CarDealershipRequestDTO;
import ar.edu.unq.compra_tu_auto.model.CarDealership;

import java.util.Optional;

public interface CarDealershipService {

    CarDealership createCarDealership(CarDealershipRequestDTO carDealershipRequestDTO);

    Optional<CarDealership> getCarDealershipWithId(Integer dealershipId);

    CarDealership updateCarDealership(Integer dealershipId, CarDealershipRequestDTO carDealershipRequestDTO);

    void deleteCarDealership(Integer dealershipId);
}
