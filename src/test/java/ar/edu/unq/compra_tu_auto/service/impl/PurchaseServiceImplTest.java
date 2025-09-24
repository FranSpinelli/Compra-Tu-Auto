package ar.edu.unq.compra_tu_auto.service.impl;

import ar.edu.unq.compra_tu_auto.controller.DTO.car.CarRequestDTO;
import ar.edu.unq.compra_tu_auto.controller.DTO.purchase.PurchaseRequestDTO;
import ar.edu.unq.compra_tu_auto.exception.ElementNotFoundException;
import ar.edu.unq.compra_tu_auto.mapper.CarMapper;
import ar.edu.unq.compra_tu_auto.model.Buyer;
import ar.edu.unq.compra_tu_auto.model.Car;
import ar.edu.unq.compra_tu_auto.model.Purchase;
import ar.edu.unq.compra_tu_auto.repository.PurchaseRepository;
import ar.edu.unq.compra_tu_auto.service.BuyerService;
import ar.edu.unq.compra_tu_auto.service.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PurchaseServiceImplTest {

    @InjectMocks
    private PurchaseServiceImpl purchaseServiceImpl;

    @Mock
    private CarService carService;

    @Mock
    private BuyerService buyerService;

    @Mock
    private CarMapper carMapper;

    @Mock
    private PurchaseRepository purchaseRepository;

    @Test
    public void creatPurchaseWithInexistentBuyerTest() {
        when(buyerService.getBuyerWithId(eq(1))).thenReturn(Optional.empty());

        PurchaseRequestDTO purchaseRequestDTO = new PurchaseRequestDTO();
        purchaseRequestDTO.setBuyerId(1);
        purchaseRequestDTO.setCarDealershipId(1);
        purchaseRequestDTO.setCarId(1);

        ElementNotFoundException exception = assertThrows(ElementNotFoundException.class,
                () -> purchaseServiceImpl.createPurchase(purchaseRequestDTO));

        assertEquals("Buyer with Id: 1 not found", exception.getMessage());
    }

    @Test
    public void creatPurchaseWithInexistentCarTest() {
        when(buyerService.getBuyerWithId(eq(1))).thenReturn(Optional.of(mock(Buyer.class)));
        when(carService.getCarWithId(eq(1), eq(1))).thenReturn(Optional.empty());

        PurchaseRequestDTO purchaseRequestDTO = new PurchaseRequestDTO();
        purchaseRequestDTO.setBuyerId(1);
        purchaseRequestDTO.setCarDealershipId(1);
        purchaseRequestDTO.setCarId(1);

        ElementNotFoundException exception = assertThrows(ElementNotFoundException.class,
                () -> purchaseServiceImpl.createPurchase(purchaseRequestDTO));

        assertEquals("Car with Id: 1 not found", exception.getMessage());
    }

    @Test
    public void purchaseTest() {
        Car car = mock(Car.class);
        //when(car.getStock()).thenReturn(1);

        when(buyerService.getBuyerWithId(eq(1))).thenReturn(Optional.of(mock(Buyer.class)));
        when(carService.getCarWithId(eq(1), eq(1))).thenReturn(Optional.of(car));

        CarRequestDTO carRequestDTO = mock(CarRequestDTO.class);
        when(carMapper.mapFromModelToRequestDto(eq(car))).thenReturn(carRequestDTO);

        when(carService.updateCar(eq(1), eq(1), eq(carRequestDTO))).thenReturn(car);

        Purchase purchase = mock(Purchase.class);
        when(purchaseRepository.savePurchase(org.mockito.ArgumentMatchers.any())).thenReturn(purchase);

        PurchaseRequestDTO purchaseRequestDTO = new PurchaseRequestDTO();
        purchaseRequestDTO.setBuyerId(1);
        purchaseRequestDTO.setCarDealershipId(1);
        purchaseRequestDTO.setCarId(1);

        Purchase result = purchaseServiceImpl.createPurchase(purchaseRequestDTO);

        assertEquals(result, purchase);
    }

    @Test
    public void getPurchaseWithId() {
        Purchase purchase = mock(Purchase.class);
        when(purchaseRepository.getPurchaseWithId(eq(1))).thenReturn(Optional.of(purchase));

        Optional<Purchase> result = purchaseServiceImpl.getPurchaseWithId(1);

        assertTrue(result.isPresent());
        assertEquals(result.get(), purchase);
    }
}