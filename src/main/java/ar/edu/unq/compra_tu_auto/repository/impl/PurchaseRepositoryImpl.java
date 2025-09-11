package ar.edu.unq.compra_tu_auto.repository.impl;

import ar.edu.unq.compra_tu_auto.mapper.PurchaseMapper;
import ar.edu.unq.compra_tu_auto.model.Purchase;
import ar.edu.unq.compra_tu_auto.repository.PurchaseRepository;
import ar.edu.unq.compra_tu_auto.repository.sqlRepository.PurchaseSqlRepository;
import ar.edu.unq.compra_tu_auto.repository.sqlRepository.entities.PurchaseEntity;
import org.springframework.stereotype.Component;

@Component
public class PurchaseRepositoryImpl implements PurchaseRepository {

    private final PurchaseSqlRepository purchaseSqlRepository;
    private final PurchaseMapper purchaseMapper;

    public PurchaseRepositoryImpl(PurchaseSqlRepository purchaseSqlRepository, PurchaseMapper purchaseMapper) {
        this.purchaseSqlRepository = purchaseSqlRepository;
        this.purchaseMapper = purchaseMapper;
    }

    @Override
    public Purchase savePurchase(Purchase purchase) {
        PurchaseEntity purchaseToBeSaved = purchaseMapper.mapFromModelToEntity(purchase);
        purchaseToBeSaved.setDeleted(false);
        PurchaseEntity savedPurchase = purchaseSqlRepository.save(purchaseToBeSaved);
        return purchaseMapper.mapFromEntityToModel(savedPurchase);
    }
}
