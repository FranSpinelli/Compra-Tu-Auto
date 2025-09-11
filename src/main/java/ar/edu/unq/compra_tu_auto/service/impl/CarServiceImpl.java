package ar.edu.unq.compra_tu_auto.service.impl;

import ar.edu.unq.compra_tu_auto.controller.DTO.car.CarRequestDTO;
import ar.edu.unq.compra_tu_auto.exception.ElementNotFoundException;
import ar.edu.unq.compra_tu_auto.mapper.CarMapper;
import ar.edu.unq.compra_tu_auto.model.Car;
import ar.edu.unq.compra_tu_auto.repository.CarRepository;
import ar.edu.unq.compra_tu_auto.service.CarDealershipService;
import ar.edu.unq.compra_tu_auto.service.CarService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private final CarMapper carMapper;
    private final CarRepository carRepository;
    private final CarDealershipService carDealershipService;

    public CarServiceImpl(CarMapper carMapper, CarRepository carRepository, CarDealershipService carDealershipService) {
        this.carMapper = carMapper;
        this.carRepository = carRepository;
        this.carDealershipService = carDealershipService;
    }

    @Override
    public Car createCar(Integer dealershipId, CarRequestDTO carRequestDTO) {
        verifyCarDealershipExists(dealershipId);

        Car car = carMapper.mapFromRequestDtoToModel(carRequestDTO);
        car.setDealershipId(dealershipId);

        return carRepository.saveCar(car);
    }

    @Override
    public Car updateCar(Integer dealershipId, Integer carId, CarRequestDTO carRequestDTO) {
        verifyCarDealershipExists(dealershipId);

        Car foundCarToEdit = carRepository.getCarByIdAndDealershipId(carId, dealershipId).orElseThrow(() -> new ElementNotFoundException("Car", carId.toString()));
        foundCarToEdit.setBrand(carRequestDTO.getBrand());
        foundCarToEdit.setModel(carRequestDTO.getModel());
        foundCarToEdit.setColor(carRequestDTO.getColor());
        foundCarToEdit.setManufactureYear(carRequestDTO.getManufactureYear());
        foundCarToEdit.setStock(carRequestDTO.getStock());
        foundCarToEdit.setPrice(carRequestDTO.getPrice());
        foundCarToEdit.setDescription(carRequestDTO.getDescription());

        return carRepository.saveCar(foundCarToEdit);
    }

    @Override
    public Optional<Car> getCarWithId(Integer dealershipId, Integer carId) {
        verifyCarDealershipExists(dealershipId);

        return carRepository.getCarByIdAndDealershipId(carId, dealershipId);
    }

    @Override
    public void deleteCar(Integer dealershipId, Integer carId) {
        verifyCarDealershipExists(dealershipId);

        carRepository.deleteCar(carId, dealershipId);
    }

    private void verifyCarDealershipExists(Integer dealershipId) {
        carDealershipService.getCarDealershipWithId(dealershipId).orElseThrow(() -> new ElementNotFoundException("Car Dealership", dealershipId.toString()));
    }
}
