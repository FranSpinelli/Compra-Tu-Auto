package ar.edu.unq.compra_tu_auto.repository.impl;

import ar.edu.unq.compra_tu_auto.mapper.CarMapper;
import ar.edu.unq.compra_tu_auto.model.Car;
import ar.edu.unq.compra_tu_auto.repository.CarRepository;
import ar.edu.unq.compra_tu_auto.repository.sqlRepository.entities.CarEntity;
import ar.edu.unq.compra_tu_auto.repository.sqlRepository.CarSqlRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CarRepositoryImpl implements CarRepository {

    private final CarMapper carMapper;
    private final CarSqlRepository carSqlRepository;

    public CarRepositoryImpl(CarMapper carMapper, CarSqlRepository carSqlRepository) {
        this.carMapper = carMapper;
        this.carSqlRepository = carSqlRepository;
    }

    @Override
    public Car saveCar(Car carToBeCreated) {
        CarEntity carToBeSaved = carMapper.mapFromModelToEntity(carToBeCreated);
        carToBeSaved.setDeleted(false);
        CarEntity savedCar = carSqlRepository.save(carToBeSaved);
        return carMapper.mapFromEntityToModel(savedCar);
    }

    @Override
    public Optional<Car> getCarWithId(Integer carId) {
        return carSqlRepository.findById(carId).filter(carEntity -> !carEntity.getDeleted()).map(carMapper::mapFromEntityToModel);
    }

    @Override
    public void deleteCar(Integer carId) {
        Optional<CarEntity> carWithId = carSqlRepository.findById(carId);

        if(carWithId.isPresent() && !carWithId.get().getDeleted()) {
            CarEntity carToBeDeleted = carWithId.get();
            carToBeDeleted.setDeleted(true);
            carSqlRepository.save(carToBeDeleted);
        }
    }

}
