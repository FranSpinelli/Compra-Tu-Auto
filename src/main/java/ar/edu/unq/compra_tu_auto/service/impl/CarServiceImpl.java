package ar.edu.unq.compra_tu_auto.service.impl;

import ar.edu.unq.compra_tu_auto.controller.DTO.CarDTO;
import ar.edu.unq.compra_tu_auto.exception.ElementNotFoundException;
import ar.edu.unq.compra_tu_auto.mapper.CarMapper;
import ar.edu.unq.compra_tu_auto.model.Car;
import ar.edu.unq.compra_tu_auto.repository.CarRepository;
import ar.edu.unq.compra_tu_auto.service.CarService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private final CarMapper carMapper;
    private final CarRepository carRepository;

    public CarServiceImpl(CarMapper carMapper, CarRepository carRepository) {
        this.carMapper = carMapper;
        this.carRepository = carRepository;
    }

    @Override
    public Car createCar(CarDTO carDTO) {
        return carRepository.saveCar(carMapper.mapFromDtoToModel(carDTO));
    }

    @Override
    public Car updateCar(Integer carId, CarDTO carDTO) {
        Car foundCarToEdit = carRepository.getCarWithId(carId).orElseThrow(() -> new ElementNotFoundException("Car", carId.toString()));

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
    public Optional<Car> getCarWithId(Integer carId) {
        return carRepository.getCarWithId(carId);
    }

    @Override
    public void deleteCar(Integer carId) {
        carRepository.deleteCar(carId);
    }

}
