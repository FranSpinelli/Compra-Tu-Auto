package ar.edu.unq.compra_tu_auto.service;

import ar.edu.unq.compra_tu_auto.controller.DTO.CarDealershipDTO;
import ar.edu.unq.compra_tu_auto.model.CarDealership;

import java.util.Optional;

public interface CarDealershipService {

    CarDealership createCarDealership(CarDealershipDTO carDealershipDTO);

    Optional<CarDealership> getCarDealershipWithId(Integer dealershipId);

    CarDealership updateCarDealership(Integer dealershipId, CarDealershipDTO carDealershipDTO);

    void deleteCarDealership(Integer dealershipId);
}
