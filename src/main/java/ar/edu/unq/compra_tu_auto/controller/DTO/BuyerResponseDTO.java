package ar.edu.unq.compra_tu_auto.controller.DTO;

import lombok.Data;

@Data
public class BuyerResponseDTO {

    private Integer Id;
    private String firstName;
    private String lastName;
    private String email;
}
