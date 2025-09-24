package ar.edu.unq.compra_tu_auto.controller.DTO.car;

import lombok.Data;

@Data
public class CarResponseDTO {

    private Integer id;
    private Integer carModelId;
    private String color;
    private Integer manufacturingYear;
    private Double price;
    private Integer dealershipId;
}
