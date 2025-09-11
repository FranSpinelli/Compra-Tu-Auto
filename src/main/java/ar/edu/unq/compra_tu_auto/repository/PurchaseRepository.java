package ar.edu.unq.compra_tu_auto.repository;

import ar.edu.unq.compra_tu_auto.model.Purchase;

import java.util.Optional;

public interface PurchaseRepository {

    Purchase savePurchase(Purchase purchaseToBeCreated);

    Optional<Purchase> getPurchaseWithId(Integer purchaseId);
}
