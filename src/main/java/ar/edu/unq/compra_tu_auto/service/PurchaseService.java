package ar.edu.unq.compra_tu_auto.service;

import ar.edu.unq.compra_tu_auto.controller.DTO.purchase.PurchaseRequestDTO;
import ar.edu.unq.compra_tu_auto.model.Purchase;

public interface PurchaseService {
    Purchase createPurchase(PurchaseRequestDTO purchaseRequestDTO);
}
