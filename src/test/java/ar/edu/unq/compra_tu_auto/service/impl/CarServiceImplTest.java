package ar.edu.unq.compra_tu_auto.service.impl;

import ar.edu.unq.compra_tu_auto.controller.DTO.CarDTO;
import ar.edu.unq.compra_tu_auto.exception.ElementNotFoundException;
import ar.edu.unq.compra_tu_auto.mapper.CarMapper;
import ar.edu.unq.compra_tu_auto.model.Car;
import ar.edu.unq.compra_tu_auto.repository.CarRepository;
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
public class CarServiceImplTest {

    @InjectMocks
    private CarServiceImpl carServiceImpl;

    @Mock
    private CarMapper carMapper;

    @Mock
    private CarRepository carRepository;

    @Test
    public void createCarTest() {
        CarDTO carDTO = mock(CarDTO.class);
        Car mockCar = mock(Car.class);
        when(carMapper.mapFromDtoToModel(eq(carDTO))).thenReturn(mockCar);
        when(carRepository.saveCar(eq(mockCar))).thenReturn(mockCar);

        Car result = carServiceImpl.createCar(carDTO);

        assertEquals(mockCar, result);
        verify(carMapper, times(1)).mapFromDtoToModel(eq(carDTO));
        verify(carRepository, times(1)).saveCar(eq(mockCar));
    }

    @Test
    public void updateCarTest() {
        Integer carId = 1;

        CarDTO carDTO = new CarDTO();
        carDTO.setBrand("Toyota");
        carDTO.setModel("Corolla");
        carDTO.setColor("Red");
        carDTO.setManufactureYear(2020);
        carDTO.setStock(10);
        carDTO.setPrice(20000.0);
        carDTO.setDescription("A reliable car");

        Car foundCar = mock(Car.class);
        Car updatedCar = mock(Car.class);

        when(carRepository.getCarWithId(eq(carId))).thenReturn(Optional.of(foundCar));
        when(carRepository.saveCar(ArgumentMatchers.any(Car.class))).thenReturn(updatedCar);

        Car result = carServiceImpl.updateCar(carId, carDTO);

        assertEquals(updatedCar, result);
        verify(carRepository, times(1)).getCarWithId(eq(carId));
        verify(foundCar, times(1)).setBrand("Toyota");
        verify(foundCar, times(1)).setModel("Corolla");
        verify(foundCar, times(1)).setColor("Red");
        verify(foundCar, times(1)).setManufactureYear(2020);
        verify(foundCar, times(1)).setStock(10);
        verify(foundCar, times(1)).setPrice(20000.0);
        verify(foundCar, times(1)).setDescription("A reliable car");
        verify(carRepository, times(1)).saveCar(eq(foundCar));
    }

    @Test
    public void updateCarWithNonExistentCarTest() {
        Integer carId = 1;
        CarDTO carDTO = new CarDTO();
        when(carRepository.getCarWithId(eq(carId))).thenReturn(Optional.empty());

        ElementNotFoundException exception = assertThrows(ElementNotFoundException.class, () -> {
            carServiceImpl.updateCar(carId, carDTO);
        });

        assertEquals("Car with Id: 1 not found", exception.getMessage());
        verify(carRepository, times(1)).getCarWithId(eq(carId));
        verify(carRepository, never()).saveCar(ArgumentMatchers.any(Car.class));
    }

    @Test
    public void getCarWithIdTest() {
        Integer carId = 1;
        Car mockCar = mock(Car.class);
        when(carRepository.getCarWithId(eq(carId))).thenReturn(Optional.of(mockCar));

        Optional<Car> result = carServiceImpl.getCarWithId(carId);

        assertEquals(mockCar, result.get());
        verify(carRepository, times(1)).getCarWithId(eq(carId));
    }

    @Test
    public void deleteCarTest() {
        Integer carId = 1;

        carServiceImpl.deleteCar(carId);

        verify(carRepository, times(1)).deleteCar(eq(carId));
    }
}