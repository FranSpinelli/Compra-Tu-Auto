package ar.edu.unq.compra_tu_auto.service.impl;

import ar.edu.unq.compra_tu_auto.controller.DTO.CarDTO;
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
    public Car createCar(Integer dealershipId, CarDTO carDTO) {
        carDealershipService.getCarDealershipWithId(dealershipId).orElseThrow(() -> new ElementNotFoundException("Car Dealership", dealershipId.toString()));

        Car car = carMapper.mapFromDtoToModel(carDTO);
        car.setDealershipId(dealershipId);

        return carRepository.saveCar(car);
    }

    @Override
    public Car updateCar(Integer dealershipId, Integer carId, CarDTO carDTO) {
        carDealershipService.getCarDealershipWithId(dealershipId).orElseThrow(() -> new ElementNotFoundException("Car Dealership", dealershipId.toString()));

        Car foundCarToEdit = carRepository.getCarByIdAndDealershipId(carId, dealershipId).orElseThrow(() -> new ElementNotFoundException("Car", carId.toString()));
        foundCarToEdit.setBrand(carDTO.getBrand());
        foundCarToEdit.setModel(carDTO.getModel());
        foundCarToEdit.setColor(carDTO.getColor());
        foundCarToEdit.setManufactureYear(carDTO.getManufactureYear());
        foundCarToEdit.setStock(carDTO.getStock());
        foundCarToEdit.setPrice(carDTO.getPrice());
        foundCarToEdit.setDescription(carDTO.getDescription());

        return carRepository.saveCar(foundCarToEdit);
    }

    @Override
    public Optional<Car> getCarWithId(Integer dealershipId, Integer carId) {
        carDealershipService.getCarDealershipWithId(dealershipId).orElseThrow(() -> new ElementNotFoundException("Car Dealership", dealershipId.toString()));

        return carRepository.getCarByIdAndDealershipId(carId, dealershipId);
    }

    @Override
    public void deleteCar(Integer dealershipId, Integer carId) {
        carDealershipService.getCarDealershipWithId(dealershipId).orElseThrow(() -> new ElementNotFoundException("Car Dealership", dealershipId.toString()));

        carRepository.deleteCar(carId, dealershipId);
    }

}
