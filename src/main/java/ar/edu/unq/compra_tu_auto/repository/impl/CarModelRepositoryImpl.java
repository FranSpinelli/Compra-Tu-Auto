package ar.edu.unq.compra_tu_auto.repository.impl;

import ar.edu.unq.compra_tu_auto.mapper.CarModelMapper;
import ar.edu.unq.compra_tu_auto.model.CarModel;
import ar.edu.unq.compra_tu_auto.repository.CarModelRepository;
import ar.edu.unq.compra_tu_auto.repository.sqlRepository.CarModelSqlRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CarModelRepositoryImpl implements CarModelRepository {

    private final CarModelSqlRepository carModelSqlRepository;
    private final CarModelMapper carModelMapper;

    public CarModelRepositoryImpl(CarModelSqlRepository carModelSqlRepository, CarModelMapper carModelMapper) {
        this.carModelSqlRepository = carModelSqlRepository;
        this.carModelMapper = carModelMapper;
    }

    @Override
    public Optional<CarModel> getCarModelById(Integer carModelId) {
        return carModelSqlRepository.findById(carModelId).map(carModelMapper::mapFromEntityToModel);
    }
}
