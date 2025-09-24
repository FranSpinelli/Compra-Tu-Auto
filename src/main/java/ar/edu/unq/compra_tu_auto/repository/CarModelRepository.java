package ar.edu.unq.compra_tu_auto.repository;

import ar.edu.unq.compra_tu_auto.model.CarModel;

import java.util.Optional;

public interface CarModelRepository {

    Optional<CarModel> getCarModelById(Integer carModelId);
}
