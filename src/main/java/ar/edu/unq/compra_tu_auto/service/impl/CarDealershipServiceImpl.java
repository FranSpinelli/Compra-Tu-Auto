package ar.edu.unq.compra_tu_auto.service.impl;

import ar.edu.unq.compra_tu_auto.controller.DTO.CarDealershipDTO;
import ar.edu.unq.compra_tu_auto.exception.ElementNotFoundException;
import ar.edu.unq.compra_tu_auto.mapper.CarDealershipMapper;
import ar.edu.unq.compra_tu_auto.model.CarDealership;
import ar.edu.unq.compra_tu_auto.repository.CarDealershipRepository;
import ar.edu.unq.compra_tu_auto.service.CarDealershipService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CarDealershipServiceImpl implements CarDealershipService {

    private final CarDealershipMapper carDealershipMapper;
    private final CarDealershipRepository carDealershipRepository;

    public CarDealershipServiceImpl(CarDealershipMapper carDealershipMapper, CarDealershipRepository carDealershipRepository) {
        this.carDealershipMapper = carDealershipMapper;
        this.carDealershipRepository = carDealershipRepository;
    }

    @Override
    public CarDealership createCarDealership(CarDealershipDTO carDealershipDTO) {
        CarDealership carDealershipToBeSaved = carDealershipMapper.mapFromDtoToModel(carDealershipDTO);
        carDealershipToBeSaved.setCars(new ArrayList<>());
        return carDealershipRepository.saveCarDealership(carDealershipToBeSaved);
    }

    @Override
    public Optional<CarDealership> getCarDealershipWithId(Integer dealershipId) {
        return carDealershipRepository.getCarDealershipWithId(dealershipId);
    }

    @Override
    public CarDealership updateCarDealership(Integer dealershipId, CarDealershipDTO carDealershipDTO) {
        CarDealership foundDealershipToEdit = carDealershipRepository.getCarDealershipWithId(dealershipId)
                .orElseThrow(() -> new ElementNotFoundException("Car Dealership", dealershipId.toString()));

        foundDealershipToEdit.setName(carDealershipDTO.getName());
        foundDealershipToEdit.setEmail(carDealershipDTO.getEmail());

        return carDealershipRepository.saveCarDealership(foundDealershipToEdit);
    }

    @Override
    public void deleteCarDealership(Integer dealershipId) {
        carDealershipRepository.deleteCarDealership(dealershipId);
    }
}
