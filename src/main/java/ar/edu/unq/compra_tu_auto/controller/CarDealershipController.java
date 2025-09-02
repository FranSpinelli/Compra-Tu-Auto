package ar.edu.unq.compra_tu_auto.controller;

import ar.edu.unq.compra_tu_auto.controller.DTO.CarDealershipDTO;
import ar.edu.unq.compra_tu_auto.controller.DTO.CarDealershipResponseDTO;
import ar.edu.unq.compra_tu_auto.mapper.CarDealershipMapper;
import ar.edu.unq.compra_tu_auto.model.CarDealership;
import ar.edu.unq.compra_tu_auto.service.CarDealershipService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                ResponseEntity.ok(carDealershipMapper.mapFromModelToDto(carDealership))
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CarDealershipResponseDTO> createCarDealership(@RequestBody @Valid CarDealershipDTO carDealershipDTO) {
        CarDealership createdCarDealership = carDealershipService.createCarDealership(carDealershipDTO);
        return ResponseEntity.ok(carDealershipMapper.mapFromModelToDto(createdCarDealership));
    }

    @PutMapping("/{dealershipId}")
    public ResponseEntity<CarDealershipResponseDTO> updateCarDealership(@PathVariable Integer dealershipId, @RequestBody @Valid CarDealershipDTO carDealershipDTO) {
        CarDealership updatedCarDealership = carDealershipService.updateCarDealership(dealershipId, carDealershipDTO);
        return ResponseEntity.ok(carDealershipMapper.mapFromModelToDto(updatedCarDealership));
    }

    @DeleteMapping("/{dealershipId}")
    public ResponseEntity<Void> deleteCarDealership(@PathVariable Integer dealershipId) {
        carDealershipService.deleteCarDealership(dealershipId);
        return ResponseEntity.noContent().build();
    }
}
