package ar.edu.unq.compra_tu_auto.model;

import lombok.Data;

@Data
public class Car {

    private Integer id;
    private Integer carModelId;
    private String color;
    private Integer manufacturingYear;
    private Double price;
    private Integer dealershipId;
    private Boolean deleted;
}
