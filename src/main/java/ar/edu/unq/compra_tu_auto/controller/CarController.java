package ar.edu.unq.compra_tu_auto.controller;

import ar.edu.unq.compra_tu_auto.controller.DTO.CarDTO;
import ar.edu.unq.compra_tu_auto.controller.DTO.CarResponseDTO;
import ar.edu.unq.compra_tu_auto.mapper.CarMapper;
import ar.edu.unq.compra_tu_auto.model.Car;
import ar.edu.unq.compra_tu_auto.service.CarService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarMapper carMapper;
    private final CarService carService;

    public CarController(CarMapper carMapper, CarService carService) {
        this.carMapper = carMapper;
        this.carService = carService;
    }

    @GetMapping("/{carId}")
    public ResponseEntity<CarResponseDTO> getCarById(@PathVariable Integer carId) {
        Optional<Car> foundCar = carService.getCarWithId(carId);

        return foundCar.map(car ->
                ResponseEntity.ok(carMapper.mapFromModelToDto(car))
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<CarResponseDTO> createCar(@RequestBody @Valid CarDTO carDTO) {
        Car createdCar = carService.createCar(carDTO);
        return ResponseEntity.ok(carMapper.mapFromModelToDto(createdCar));
    }

    @PutMapping("/{carId}")
    public ResponseEntity<CarResponseDTO> updateCar(@PathVariable Integer carId, @RequestBody @Valid CarDTO carDTO) {
        Car updatedCar = carService.updateCar(carId, carDTO);
        return ResponseEntity.ok(carMapper.mapFromModelToDto(updatedCar));
    }

    @DeleteMapping("/{carId}")
    public ResponseEntity<Void> deleteCar(@PathVariable Integer carId) {
        carService.deleteCar(carId);
        return ResponseEntity.noContent().build();
    }
}
