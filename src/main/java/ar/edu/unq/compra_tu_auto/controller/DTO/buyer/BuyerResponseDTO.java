package ar.edu.unq.compra_tu_auto.controller.DTO.buyer;

import lombok.Data;

@Data
public class BuyerResponseDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
}
