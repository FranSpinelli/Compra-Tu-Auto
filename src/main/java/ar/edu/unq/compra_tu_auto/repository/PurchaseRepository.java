package ar.edu.unq.compra_tu_auto.repository;

import ar.edu.unq.compra_tu_auto.model.Purchase;

public interface PurchaseRepository {

    Purchase savePurchase(Purchase purchaseToBeCreated);
}
