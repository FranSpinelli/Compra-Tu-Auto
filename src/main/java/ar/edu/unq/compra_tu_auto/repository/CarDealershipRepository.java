package ar.edu.unq.compra_tu_auto.repository;

import ar.edu.unq.compra_tu_auto.model.CarDealership;

import java.util.Optional;

public interface CarDealershipRepository {

    CarDealership saveCarDealership(CarDealership carDealership);

    Optional<CarDealership> getCarDealershipWithId(Integer dealershipId);

    void deleteCarDealership(Integer dealershipId);
}
