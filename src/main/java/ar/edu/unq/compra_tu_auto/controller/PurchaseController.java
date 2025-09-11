package ar.edu.unq.compra_tu_auto.controller;

import ar.edu.unq.compra_tu_auto.controller.DTO.purchase.PurchaseResponseDTO;
import ar.edu.unq.compra_tu_auto.controller.DTO.purchase.PurchaseRequestDTO;
import ar.edu.unq.compra_tu_auto.mapper.PurchaseMapper;
import ar.edu.unq.compra_tu_auto.model.Purchase;
import ar.edu.unq.compra_tu_auto.service.PurchaseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;
    private final PurchaseMapper purchaseMapper;

    public PurchaseController(PurchaseService purchaseService, PurchaseMapper purchaseMapper) {
        this.purchaseService = purchaseService;
        this.purchaseMapper = purchaseMapper;
    }

    @PostMapping()
    public ResponseEntity<PurchaseResponseDTO> createPurchase(@RequestBody @Valid PurchaseRequestDTO purchaseRequestDTO) {
        Purchase createdePurchase = purchaseService.createPurchase(purchaseRequestDTO);
        return ResponseEntity.status(201).body(purchaseMapper.mapFromModelToResponseDTO(createdePurchase));
    }
}
