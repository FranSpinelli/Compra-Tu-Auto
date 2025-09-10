package ar.edu.unq.compra_tu_auto.service.impl;

import ar.edu.unq.compra_tu_auto.controller.DTO.BuyerDTO;
import ar.edu.unq.compra_tu_auto.model.Buyer;
import ar.edu.unq.compra_tu_auto.mapper.BuyerMapper;
import ar.edu.unq.compra_tu_auto.repository.BuyerRepository;
import ar.edu.unq.compra_tu_auto.exception.ElementNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BuyerServiceImplTest {

    @InjectMocks
    private BuyerServiceImpl buyerServiceImpl;

    @Mock
    private BuyerRepository buyerRepository;

    @Mock
    private BuyerMapper buyerMapper;

    @Test
    public void createBuyerTest() {
        BuyerDTO buyerDTO = mock(BuyerDTO.class);
        Buyer mockBuyer = mock(Buyer.class);
        when(buyerMapper.mapFromDTOToModel(eq(buyerDTO))).thenReturn(mockBuyer);
        when(buyerRepository.saveBuyer(eq(mockBuyer))).thenReturn(mockBuyer);

        Buyer result = buyerServiceImpl.createBuyer(buyerDTO);

        assertEquals(mockBuyer, result);
        verify(buyerRepository, times(1)).saveBuyer(eq(mockBuyer));
        verify(buyerMapper, times(1)).mapFromDTOToModel(eq(buyerDTO));
    }

    @Test
    public void updateBuyerTest() {
        Integer buyerId = 1;

        BuyerDTO buyerDTO = new BuyerDTO();
        buyerDTO.setFirstName("foo");
        buyerDTO.setLastName("bar");
        buyerDTO.setEmail("baz@gmail.com");

        Buyer foundBuyer = mock(Buyer.class);
        Buyer updatedBuyer = mock(Buyer.class);

        when(buyerRepository.getBuyerWithId(eq(buyerId))).thenReturn(Optional.of(foundBuyer));
        when(buyerRepository.saveBuyer(ArgumentMatchers.any(Buyer.class))).thenReturn(updatedBuyer);

        Buyer result = buyerServiceImpl.updateBuyer(buyerId, buyerDTO);

        assertEquals(updatedBuyer, result);
        verify(buyerRepository, times(1)).getBuyerWithId(eq(buyerId));
        verify(foundBuyer, times(1)).setFirstName("foo");
        verify(foundBuyer, times(1)).setLastName("bar");
        verify(foundBuyer, times(1)).setEmail("baz@gmail.com");
        verify(buyerRepository, times(1)).saveBuyer(eq(foundBuyer));
    }

    @Test
    public void updateBuyerWithNonExistentBuyerTest() {
        Integer buyerId = 1;
        BuyerDTO buyerDTO = new BuyerDTO();
        when(buyerRepository.getBuyerWithId(eq(buyerId))).thenReturn(Optional.empty());

        ElementNotFoundException exception = assertThrows(ElementNotFoundException.class, () ->
                buyerServiceImpl.updateBuyer(buyerId, buyerDTO));

        assertEquals("Buyer with Id: " + buyerId + " not found", exception.getMessage());
        verify(buyerRepository, times(1)).getBuyerWithId(eq(buyerId));
        verify(buyerRepository, never()).saveBuyer(ArgumentMatchers.any(Buyer.class));
    }

    @Test
    public void getBuyerByIdTest() {
        Integer buyerId = 1;
        Buyer mockBuyer = mock(Buyer.class);
        when(buyerRepository.getBuyerWithId(eq(buyerId))).thenReturn(Optional.of(mockBuyer));

        Optional<Buyer> result = buyerServiceImpl.getBuyerWithId(buyerId);

        assertEquals(mockBuyer, result.get());
        verify(buyerRepository, times(1)).getBuyerWithId(eq(buyerId));
    }

    @Test
    public void deleteBuyerTest() {
        Integer buyerId = 1;

        buyerServiceImpl.deleteBuyer(buyerId);

        verify(buyerRepository, times(1)).deleteBuyerById(eq(buyerId));
    }
}
