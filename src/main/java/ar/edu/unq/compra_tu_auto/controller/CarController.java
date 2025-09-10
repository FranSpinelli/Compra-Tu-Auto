package ar.edu.unq.compra_tu_auto.controller;

import ar.edu.unq.compra_tu_auto.controller.DTO.CarDTO;
import ar.edu.unq.compra_tu_auto.controller.DTO.CarResponseDTO;
import ar.edu.unq.compra_tu_auto.mapper.CarMapper;
import ar.edu.unq.compra_tu_auto.model.Car;
import ar.edu.unq.compra_tu_auto.service.CarService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<CarResponseDTO> createCar(@PathVariable Integer dealershipId, @RequestBody @Valid CarDTO carDTO) {
        Car createdCar = carService.createCar(dealershipId, carDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(carMapper.mapFromModelToDto(createdCar));
    }

    @PutMapping("/{carId}")
    public ResponseEntity<CarResponseDTO> updateCar(@PathVariable Integer dealershipId, @PathVariable Integer carId, @RequestBody @Valid CarDTO carDTO) {
        Car updatedCar = carService.updateCar(dealershipId, carId, carDTO);
        return ResponseEntity.ok(carMapper.mapFromModelToDto(updatedCar));
    }

    @DeleteMapping("/{carId}")
    public ResponseEntity<Void> deleteCar(@PathVariable Integer dealershipId, @PathVariable Integer carId) {
        carService.deleteCar(dealershipId, carId);
        return ResponseEntity.noContent().build();
    }
}
