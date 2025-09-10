package ar.edu.unq.compra_tu_auto.controller;

import ar.edu.unq.compra_tu_auto.controller.DTO.car.CarRequestDTO;
import ar.edu.unq.compra_tu_auto.controller.DTO.car.CarResponseDTO;
import ar.edu.unq.compra_tu_auto.mapper.CarMapper;
import ar.edu.unq.compra_tu_auto.model.Car;
import ar.edu.unq.compra_tu_auto.service.CarService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.Optional;

@RestController
@RequestMapping("/car-dealerships/{dealershipId}/cars")
public class CarController {

    private final CarMapper carMapper;
    private final CarService carService;

    public CarController(CarMapper carMapper, CarService carService) {
        this.carMapper = carMapper;
        this.carService = carService;
    }

    @GetMapping("/{carId}")
    public ResponseEntity<CarResponseDTO> getCarById(@PathVariable Integer dealershipId, @PathVariable Integer carId) {
        Optional<Car> foundCar = carService.getCarWithId(dealershipId, carId);

        return foundCar.map(car ->
                ResponseEntity.ok(carMapper.mapFromModelToDto(car))
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<CarResponseDTO> createCar(@PathVariable Integer dealershipId, @RequestBody @Valid CarRequestDTO carRequestDTO) {
        Car createdCar = carService.createCar(dealershipId, carRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(carMapper.mapFromModelToDto(createdCar));
    }

    @PutMapping("/{carId}")
    public ResponseEntity<CarResponseDTO> updateCar(@PathVariable Integer dealershipId, @PathVariable Integer carId, @RequestBody @Valid CarRequestDTO carRequestDTO) {
        Car updatedCar = carService.updateCar(dealershipId, carId, carRequestDTO);
        return ResponseEntity.ok(carMapper.mapFromModelToDto(updatedCar));
    }

    @DeleteMapping("/{carId}")
    public ResponseEntity<Void> deleteCar(@PathVariable Integer dealershipId, @PathVariable Integer carId) {
        carService.deleteCar(dealershipId, carId);
        return ResponseEntity.noContent().build();
    }
}
