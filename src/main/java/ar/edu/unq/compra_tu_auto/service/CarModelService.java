package ar.edu.unq.compra_tu_auto.service;

import ar.edu.unq.compra_tu_auto.model.CarModel;

import java.util.Optional;

public interface CarModelService {

    Optional<CarModel> getCarModelWithId(Integer carModelId);
}
