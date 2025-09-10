package ar.edu.unq.compra_tu_auto.model;

import lombok.Data;

import java.util.List;

@Data
public class CarDealership {

    private Integer id;
    private String name;
    private String email;
    private List<Car> cars;

    public void addCar(Car car){
        this.cars.add(car);
    }
}
