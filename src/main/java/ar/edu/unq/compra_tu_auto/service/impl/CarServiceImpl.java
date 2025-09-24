package ar.edu.unq.compra_tu_auto.service.impl;

import ar.edu.unq.compra_tu_auto.controller.DTO.car.CarRequestDTO;
import ar.edu.unq.compra_tu_auto.exception.ElementNotFoundException;
import ar.edu.unq.compra_tu_auto.exception.InvalidCarSpecificationException;
import ar.edu.unq.compra_tu_auto.mapper.CarMapper;
import ar.edu.unq.compra_tu_auto.model.Car;
import ar.edu.unq.compra_tu_auto.model.CarModel;
import ar.edu.unq.compra_tu_auto.repository.CarRepository;
import ar.edu.unq.compra_tu_auto.service.CarDealershipService;
import ar.edu.unq.compra_tu_auto.service.CarModelService;
import ar.edu.unq.compra_tu_auto.service.CarService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private final CarMapper carMapper;
    private final CarRepository carRepository;
    private final CarDealershipService carDealershipService;
    private final CarModelService carModelService;

    public CarServiceImpl(CarMapper carMapper, CarRepository carRepository, CarDealershipService carDealershipService, CarModelService carModelService) {
        this.carMapper = carMapper;
        this.carRepository = carRepository;
        this.carDealershipService = carDealershipService;
        this.carModelService = carModelService;
    }

    @Override
    public Car createCar(Integer dealershipId, CarRequestDTO carRequestDTO) {
        verifyCarDealershipExists(dealershipId);
        returnIfCarModelExistsAndHasColorAndManufacturingYear(carRequestDTO);

        Car car = carMapper.mapFromRequestDtoToModel(carRequestDTO);
        car.setDealershipId(dealershipId);

        return carRepository.saveCar(car);
    }

    @Override
    public Car updateCar(Integer dealershipId, Integer carId, CarRequestDTO carRequestDTO) {
        verifyCarDealershipExists(dealershipId);
        CarModel carModel = returnIfCarModelExistsAndHasColorAndManufacturingYear(carRequestDTO);

        Car foundCarToEdit = carRepository.getCarByIdAndDealershipId(carId, dealershipId).orElseThrow(() -> new ElementNotFoundException("Car", carId.toString()));
        foundCarToEdit.setCarModel(carModel);
        foundCarToEdit.setColor(carRequestDTO.getColor());
        foundCarToEdit.setManufacturingYear(carRequestDTO.getManufacturingYear());
        foundCarToEdit.setPrice(carRequestDTO.getPrice());

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

    private CarModel returnIfCarModelExistsAndHasColorAndManufacturingYear(CarRequestDTO carRequestDTO) {
        CarModel carModel = carModelService.getCarModelWithId(carRequestDTO.getCarModelId()).orElseThrow(() -> new ElementNotFoundException("Car Model", carRequestDTO.getCarModelId().toString()));
        if(carModel.getColors().stream().noneMatch(carModelColor -> carModelColor.equalsIgnoreCase(carRequestDTO.getColor()))){
            throw new InvalidCarSpecificationException("Color", carRequestDTO.getColor(), carModel.getId().toString());
        }

        if(carModel.getManufacturingYears().stream().noneMatch(carModelManufacturingYear -> carModelManufacturingYear.equals(carRequestDTO.getManufacturingYear()))){
            throw new InvalidCarSpecificationException("Manufacturing Year", carRequestDTO.getManufacturingYear().toString(), carModel.getId().toString());
        }

        return carModel;
    }
}
