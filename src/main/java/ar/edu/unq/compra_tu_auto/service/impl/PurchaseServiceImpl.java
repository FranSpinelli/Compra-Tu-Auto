package ar.edu.unq.compra_tu_auto.service.impl;

import ar.edu.unq.compra_tu_auto.controller.DTO.purchase.PurchaseRequestDTO;
import ar.edu.unq.compra_tu_auto.exception.ElementNotFoundException;
import ar.edu.unq.compra_tu_auto.exception.InsufficientStockException;
import ar.edu.unq.compra_tu_auto.mapper.CarMapper;
import ar.edu.unq.compra_tu_auto.model.Buyer;
import ar.edu.unq.compra_tu_auto.model.Car;
import ar.edu.unq.compra_tu_auto.model.Purchase;
import ar.edu.unq.compra_tu_auto.repository.PurchaseRepository;
import ar.edu.unq.compra_tu_auto.service.BuyerService;
import ar.edu.unq.compra_tu_auto.service.CarService;
import ar.edu.unq.compra_tu_auto.service.PurchaseService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final CarService carService;
    private final BuyerService buyerService;
    private final CarMapper carMapper;
    private final PurchaseRepository purchaseRepository;

    public PurchaseServiceImpl(CarService carService, BuyerService buyerService, CarMapper carMapper, PurchaseRepository purchaseRepository) {
        this.carService = carService;
        this.buyerService = buyerService;
        this.carMapper = carMapper;
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public Purchase createPurchase(PurchaseRequestDTO purchaseRequestDTO) {
        Buyer buyer = buyerService.getBuyerWithId(purchaseRequestDTO.getBuyerId())
                .orElseThrow(() -> new ElementNotFoundException("Buyer", purchaseRequestDTO.getBuyerId().toString()));

        Car carToBePurchased = carService.getCarWithId(purchaseRequestDTO.getCarDealershipId(), purchaseRequestDTO.getCarId())
                    .orElseThrow(() -> new ElementNotFoundException("Car", purchaseRequestDTO.getCarId().toString()));

        if (carToBePurchased.getStock() < 1) {
            throw new InsufficientStockException("Car", carToBePurchased.getId());
        }

        carToBePurchased.setStock(carToBePurchased.getStock() - 1);
        carService.updateCar(purchaseRequestDTO.getCarDealershipId(), purchaseRequestDTO.getBuyerId(), carMapper.mapFromModelToRequestDto(carToBePurchased));

        Purchase purchaseToBeCreated = new Purchase(null, buyer, carToBePurchased, false);
        return purchaseRepository.savePurchase(purchaseToBeCreated);
    }

    @Override
    public Optional<Purchase> getPurchaseWithId(Integer purchaseId) {
        return purchaseRepository.getPurchaseWithId(purchaseId);
    }
}
