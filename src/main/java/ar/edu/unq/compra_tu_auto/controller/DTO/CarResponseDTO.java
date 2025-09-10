package ar.edu.unq.compra_tu_auto.controller.DTO;

import lombok.Data;

@Data
public class CarResponseDTO {

    private Integer id;
    private String brand;
    private String model;
    private String color;
    private Integer manufactureYear;
    private Integer stock;
    private Double price;
    private String description;
    private Integer dealershipId;
}
