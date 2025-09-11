package ar.edu.unq.compra_tu_auto.service.impl;

import ar.edu.unq.compra_tu_auto.controller.DTO.carDealership.CarDealershipRequestDTO;
import ar.edu.unq.compra_tu_auto.exception.ElementNotFoundException;
import ar.edu.unq.compra_tu_auto.mapper.CarDealershipMapper;
import ar.edu.unq.compra_tu_auto.model.CarDealership;
import ar.edu.unq.compra_tu_auto.repository.CarDealershipRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarDealershipServiceImplTest {

    @InjectMocks
    private CarDealershipServiceImpl carDealershipServiceImpl;

    @Mock
    private CarDealershipMapper carDealershipMapper;

    @Mock
    private CarDealershipRepository carDealershipRepository;

    @Test
    public void createCarTest() {
        CarDealershipRequestDTO carDealershipRequestDTO = mock(CarDealershipRequestDTO.class);
        CarDealership carDealership = mock(CarDealership.class);
        when(carDealershipMapper.mapFromDtoToModel(eq(carDealershipRequestDTO))).thenReturn(carDealership);
        when(carDealershipRepository.saveCarDealership(eq(carDealership))).thenReturn(carDealership);

        CarDealership result = carDealershipServiceImpl.createCarDealership(carDealershipRequestDTO);

        assertEquals(carDealership, result);
        verify(carDealershipMapper, times(1)).mapFromDtoToModel(eq(carDealershipRequestDTO));
        verify(carDealershipRepository, times(1)).saveCarDealership(eq(carDealership));
    }

    @Test
    public void updateCarTest() {
        Integer carDealershipId = 1;

        CarDealershipRequestDTO carDealershipRequestDTO = new CarDealershipRequestDTO();
        carDealershipRequestDTO.setName("Una Consecionaria");
        carDealershipRequestDTO.setEmail("unMail@gmail.com");

        CarDealership foundCar = mock(CarDealership.class);
        CarDealership updatedCar = mock(CarDealership.class);

        when(carDealershipRepository.getCarDealershipWithId(eq(carDealershipId))).thenReturn(Optional.of(foundCar));
        when(carDealershipRepository.saveCarDealership(ArgumentMatchers.any(CarDealership.class))).thenReturn(updatedCar);

        CarDealership result = carDealershipServiceImpl.updateCarDealership(carDealershipId, carDealershipRequestDTO);

        assertEquals(updatedCar, result);
        verify(carDealershipRepository, times(1)).getCarDealershipWithId(eq(carDealershipId));
        verify(foundCar, times(1)).setName("Una Consecionaria");
        verify(foundCar, times(1)).setEmail("unMail@gmail.com");
        verify(carDealershipRepository, times(1)).saveCarDealership(eq(foundCar));
    }

    @Test
    public void updateCarWithNonExistentCarTest() {
        Integer carDealershipId = 1;
        CarDealershipRequestDTO carDealershipRequestDTO = new CarDealershipRequestDTO();
        when(carDealershipRepository.getCarDealershipWithId(eq(carDealershipId))).thenReturn(Optional.empty());

        ElementNotFoundException exception = assertThrows(ElementNotFoundException.class, () -> {
            carDealershipServiceImpl.updateCarDealership(carDealershipId, carDealershipRequestDTO);
        });

        assertEquals("Car Dealership with Id: 1 not found", exception.getMessage());
        verify(carDealershipRepository, times(1)).getCarDealershipWithId(eq(carDealershipId));
        verify(carDealershipRepository, never()).saveCarDealership(ArgumentMatchers.any(CarDealership.class));
    }

    @Test
    public void getCarWithIdTest() {
        Integer carDealershipId = 1;
        CarDealership mockCarDealership = mock(CarDealership.class);
        when(carDealershipRepository.getCarDealershipWithId(eq(carDealershipId))).thenReturn(Optional.of(mockCarDealership));

        Optional<CarDealership> result = carDealershipServiceImpl.getCarDealershipWithId(carDealershipId);

        assertTrue(result.isPresent());
        assertEquals(mockCarDealership, result.get());
        verify(carDealershipRepository, times(1)).getCarDealershipWithId(eq(carDealershipId));
    }

    @Test
    public void deleteCarTest() {
        Integer carDealershipId = 1;

        carDealershipServiceImpl.deleteCarDealership(carDealershipId);

        verify(carDealershipRepository, times(1)).deleteCarDealership(eq(carDealershipId));
    }
}