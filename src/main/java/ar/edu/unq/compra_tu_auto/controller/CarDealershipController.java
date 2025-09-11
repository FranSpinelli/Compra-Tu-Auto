package ar.edu.unq.compra_tu_auto.controller;

import ar.edu.unq.compra_tu_auto.controller.DTO.carDealership.CarDealershipRequestDTO;
import ar.edu.unq.compra_tu_auto.controller.DTO.carDealership.CarDealershipResponseDTO;
import ar.edu.unq.compra_tu_auto.mapper.CarDealershipMapper;
import ar.edu.unq.compra_tu_auto.model.CarDealership;
import ar.edu.unq.compra_tu_auto.service.CarDealershipService;
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
@RequestMapping("/car-dealerships")
public class CarDealershipController {

    private final CarDealershipMapper carDealershipMapper;
    private final CarDealershipService carDealershipService;

    public CarDealershipController(CarDealershipMapper carDealershipMapper, CarDealershipService carDealershipService) {
        this.carDealershipMapper = carDealershipMapper;
        this.carDealershipService = carDealershipService;
    }

    @GetMapping("/{dealershipId}")
    public ResponseEntity<CarDealershipResponseDTO> getCarDealershipById(@PathVariable Integer dealershipId) {
        Optional<CarDealership> foundCarDealership = carDealershipService.getCarDealershipWithId(dealershipId);

        return foundCarDealership.map(carDealership ->
                ResponseEntity.ok(carDealershipMapper.mapFromModelToResponseDto(carDealership))
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CarDealershipResponseDTO> createCarDealership(@RequestBody @Valid CarDealershipRequestDTO carDealershipRequestDTO) {
        CarDealership createdCarDealership = carDealershipService.createCarDealership(carDealershipRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(carDealershipMapper.mapFromModelToResponseDto(createdCarDealership));
    }

    @PutMapping("/{dealershipId}")
    public ResponseEntity<CarDealershipResponseDTO> updateCarDealership(@PathVariable Integer dealershipId, @RequestBody @Valid CarDealershipRequestDTO carDealershipRequestDTO) {
        CarDealership updatedCarDealership = carDealershipService.updateCarDealership(dealershipId, carDealershipRequestDTO);
        return ResponseEntity.ok(carDealershipMapper.mapFromModelToResponseDto(updatedCarDealership));
    }

    @DeleteMapping("/{dealershipId}")
    public ResponseEntity<Void> deleteCarDealership(@PathVariable Integer dealershipId) {
        carDealershipService.deleteCarDealership(dealershipId);
        return ResponseEntity.noContent().build();
    }
}
