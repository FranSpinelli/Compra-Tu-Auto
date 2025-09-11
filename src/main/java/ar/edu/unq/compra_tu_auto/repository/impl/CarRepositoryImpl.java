package ar.edu.unq.compra_tu_auto.repository.impl;

import ar.edu.unq.compra_tu_auto.exception.ElementNotFoundException;
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
    public Optional<Car> getCarByIdAndDealershipId(Integer carId, Integer dealershipId) {
        return carSqlRepository.findByIdAndCarDealershipId(carId, dealershipId).filter(carEntity -> !carEntity.getDeleted()).map(carMapper::mapFromEntityToModel);
    }

    @Override
    public void deleteCar(Integer carId, Integer dealershipId) {
        CarEntity carToBeDeleted = carSqlRepository.findByIdAndCarDealershipId(carId, dealershipId).orElseThrow(() -> new ElementNotFoundException("Car", carId.toString()));

        if (!carToBeDeleted.getDeleted()) {
            carToBeDeleted.setDeleted(true);
            carSqlRepository.save(carToBeDeleted);
        }
    }

}
