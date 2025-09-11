package ar.edu.unq.compra_tu_auto.service.impl;

import ar.edu.unq.compra_tu_auto.controller.DTO.car.CarRequestDTO;
import ar.edu.unq.compra_tu_auto.exception.ElementNotFoundException;
import ar.edu.unq.compra_tu_auto.mapper.CarMapper;
import ar.edu.unq.compra_tu_auto.model.Car;
import ar.edu.unq.compra_tu_auto.model.CarDealership;
import ar.edu.unq.compra_tu_auto.repository.CarRepository;
import ar.edu.unq.compra_tu_auto.service.CarDealershipService;
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

    @Mock
    private CarDealershipService carDealershipService;

    @Test
    public void createCarTest() {
        CarRequestDTO carRequestDTO = mock(CarRequestDTO.class);
        Car mockCar = mock(Car.class);

        when(carDealershipService.getCarDealershipWithId(eq(1))).thenReturn(Optional.of(mock(CarDealership.class)));
        when(carMapper.mapFromDtoToModel(eq(carRequestDTO))).thenReturn(mockCar);
        when(carRepository.saveCar(eq(mockCar))).thenReturn(mockCar);

        Car result = carServiceImpl.createCar(1, carRequestDTO);

        assertEquals(mockCar, result);
        verify(carMapper, times(1)).mapFromDtoToModel(eq(carRequestDTO));
        verify(carRepository, times(1)).saveCar(eq(mockCar));
    }

    @Test
    public void updateCarTest() {
        Integer carDealershipId = 1;
        Integer carId = 1;

        CarRequestDTO carRequestDTO = new CarRequestDTO();
        carRequestDTO.setBrand("Toyota");
        carRequestDTO.setModel("Corolla");
        carRequestDTO.setColor("Red");
        carRequestDTO.setManufactureYear(2020);
        carRequestDTO.setStock(10);
        carRequestDTO.setPrice(20000.0);
        carRequestDTO.setDescription("A reliable car");

        Car foundCar = mock(Car.class);
        Car updatedCar = mock(Car.class);

        when(carDealershipService.getCarDealershipWithId(eq(carDealershipId))).thenReturn(Optional.of(mock(CarDealership.class)));
        when(carRepository.getCarByIdAndDealershipId(eq(carId), eq(carDealershipId))).thenReturn(Optional.of(foundCar));
        when(carRepository.saveCar(ArgumentMatchers.any(Car.class))).thenReturn(updatedCar);

        Car result = carServiceImpl.updateCar(carDealershipId, carId, carRequestDTO);

        assertEquals(updatedCar, result);
        verify(carRepository, times(1)).getCarByIdAndDealershipId(eq(carId), eq(carDealershipId));
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
        Integer carDealershipId = 1;
        Integer carId = 1;
        CarRequestDTO carRequestDTO = new CarRequestDTO();
        when(carDealershipService.getCarDealershipWithId(eq(carDealershipId))).thenReturn(Optional.of(mock(CarDealership.class)));
        when(carRepository.getCarByIdAndDealershipId(eq(carId), eq(carDealershipId))).thenReturn(Optional.empty());

        ElementNotFoundException exception = assertThrows(ElementNotFoundException.class, () -> {
            carServiceImpl.updateCar(carId, carId, carRequestDTO);
        });

        assertEquals("Car with Id: 1 not found", exception.getMessage());
        verify(carRepository, times(1)).getCarByIdAndDealershipId(eq(carId), eq(carDealershipId));
        verify(carRepository, never()).saveCar(ArgumentMatchers.any(Car.class));
    }

    @Test
    public void getCarWithIdTest() {
        Integer carDealershipId = 1;
        Integer carId = 1;
        Car mockCar = mock(Car.class);
        when(carDealershipService.getCarDealershipWithId(eq(carDealershipId))).thenReturn(Optional.of(mock(CarDealership.class)));
        when(carRepository.getCarByIdAndDealershipId(eq(carId), eq(carDealershipId))).thenReturn(Optional.of(mockCar));

        Optional<Car> result = carServiceImpl.getCarWithId(carId, carId);

        assertEquals(mockCar, result.get());
        verify(carRepository, times(1)).getCarByIdAndDealershipId(eq(carId), eq(carDealershipId));
    }

    @Test
    public void deleteCarTest() {
        Integer carDealershipId = 1;
        Integer carId = 1;

        when(carDealershipService.getCarDealershipWithId(eq(carDealershipId))).thenReturn(Optional.of(mock(CarDealership.class)));
        carServiceImpl.deleteCar(carId, carId);

        verify(carRepository, times(1)).deleteCar(eq(carId), eq(carDealershipId));
    }
}