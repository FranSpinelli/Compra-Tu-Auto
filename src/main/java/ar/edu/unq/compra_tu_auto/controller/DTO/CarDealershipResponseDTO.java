package ar.edu.unq.compra_tu_auto.controller.DTO;

import lombok.Data;

import java.util.List;

@Data
public class CarDealershipResponseDTO {

    private Integer id;
    private String name;
    private String email;
    private List<CarResponseDTO> cars;
}
