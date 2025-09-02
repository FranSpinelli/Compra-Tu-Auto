package ar.edu.unq.compra_tu_auto.controller.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CarDealershipDTO {

    @NotBlank(message = "Name field is required")
    @Size(min = 2, max = 100, message = "Name field length must be between 2 and 100 characters.")
    private String name;

    @NotBlank(message = "Email field is required")
    @Size(min = 5, max = 100, message = "Email field length must be between 5 and 100 characters.")
    @Email(message = "Email field must be in a valid format.")
    private String email;
}
