package ar.edu.unq.compra_tu_auto.controller;

import ar.edu.unq.compra_tu_auto.controller.DTO.buyer.BuyerRequestDTO;
import ar.edu.unq.compra_tu_auto.controller.DTO.buyer.BuyerResponseDTO;
import ar.edu.unq.compra_tu_auto.mapper.BuyerMapper;
import ar.edu.unq.compra_tu_auto.model.Buyer;
import ar.edu.unq.compra_tu_auto.service.BuyerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/buyers")
public class BuyerController {

    private final BuyerMapper buyerMapper;
    private final BuyerService buyerService;

    public BuyerController(BuyerMapper buyerMapper, BuyerService buyerService) {
        this.buyerMapper = buyerMapper;
        this.buyerService = buyerService;
    }

    @GetMapping("/{buyerId}")
    public ResponseEntity<BuyerResponseDTO> getBuyerById(@PathVariable Integer buyerId){
        Optional<Buyer> foundBuyer = buyerService.getBuyerWithId(buyerId);

        return foundBuyer.map(buyer ->
                ResponseEntity.ok(buyerMapper.mapFromModelToDTO(buyer)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<BuyerResponseDTO> createBuyer(@RequestBody @Valid BuyerRequestDTO buyerDTO){
        Buyer buyer = buyerService.createBuyer(buyerDTO);
        return ResponseEntity.ok(buyerMapper.mapFromModelToDTO(buyer));
    }

    @PutMapping("/{buyerId}")
    public ResponseEntity<BuyerResponseDTO> updateBuyer(@PathVariable Integer buyerId, @RequestBody @Valid BuyerRequestDTO buyerDTO){
        Buyer updatedBuyer = buyerService.updateBuyer(buyerId, buyerDTO);
        return ResponseEntity.ok(buyerMapper.mapFromModelToDTO(updatedBuyer));
    }

    @DeleteMapping("/{buyerId}")
    public ResponseEntity<Void>  deleteBuyer(@PathVariable Integer buyerId){
        buyerService.deleteBuyer(buyerId);
        return ResponseEntity.noContent().build();
    }

}
