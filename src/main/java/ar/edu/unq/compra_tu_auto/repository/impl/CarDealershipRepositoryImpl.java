package ar.edu.unq.compra_tu_auto.repository.impl;

import ar.edu.unq.compra_tu_auto.exception.ElementNotFoundException;
import ar.edu.unq.compra_tu_auto.mapper.CarDealershipMapper;
import ar.edu.unq.compra_tu_auto.model.CarDealership;
import ar.edu.unq.compra_tu_auto.repository.CarDealershipRepository;
import ar.edu.unq.compra_tu_auto.repository.sqlRepository.CarDealershipSqlRepository;
import ar.edu.unq.compra_tu_auto.repository.sqlRepository.entities.CarDealershipEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CarDealershipRepositoryImpl implements CarDealershipRepository {

    private final CarDealershipMapper carDealershipMapper;
    private final CarDealershipSqlRepository carDealershipSqlRepository;

    public CarDealershipRepositoryImpl(CarDealershipMapper carDealershipMapper, CarDealershipSqlRepository carDealershipSqlRepository) {
        this.carDealershipMapper = carDealershipMapper;
        this.carDealershipSqlRepository = carDealershipSqlRepository;
    }

    @Override
    public CarDealership saveCarDealership(CarDealership carDealership) {
        CarDealershipEntity carDealershipToBeSaved = carDealershipMapper.mapFromModelToEntity(carDealership);
        carDealershipToBeSaved.setDeleted(false);
        CarDealershipEntity savedCarDealership = carDealershipSqlRepository.save(carDealershipToBeSaved);
        return carDealershipMapper.mapFromEntityToModel(savedCarDealership);
    }

    @Override
    public Optional<CarDealership> getCarDealershipWithId(Integer dealershipId) {
        return carDealershipSqlRepository.findById(dealershipId)
                .filter(carDealershipEntity -> !carDealershipEntity.getDeleted())
                .map(carDealershipMapper::mapFromEntityToModel);
    }

    @Override
    public void deleteCarDealership(Integer dealershipId) {
        CarDealershipEntity carDealershipToBeDeleted = carDealershipSqlRepository.findById(dealershipId).orElseThrow(() -> new ElementNotFoundException("Car Dealership", dealershipId.toString()));

        if(!carDealershipToBeDeleted.getDeleted()) {
            carDealershipToBeDeleted.setDeleted(true);
            carDealershipSqlRepository.save(carDealershipToBeDeleted);
        }
    }

}
