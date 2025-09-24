package ar.edu.unq.compra_tu_auto.service.impl;

import ar.edu.unq.compra_tu_auto.model.CarModel;
import ar.edu.unq.compra_tu_auto.repository.CarModelRepository;
import ar.edu.unq.compra_tu_auto.service.CarModelService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarModelServiceImpl implements CarModelService {

    private final CarModelRepository carModelRepository;

    public CarModelServiceImpl(CarModelRepository carModelRepository) {
        this.carModelRepository = carModelRepository;
    }

    @Override
    public Optional<CarModel> getCarModelWithId(Integer carModelId) {
        return carModelRepository.getCarModelById(carModelId);
    }
}
