package ar.edu.unq.compra_tu_auto.model;

import lombok.Data;

@Data
public class Car {

    private Integer carId;
    private String brand;
    private String model;
    private String color;
    private Integer manufactureYear;
    private Integer stock;
    private Double price;
    private String description;
}
