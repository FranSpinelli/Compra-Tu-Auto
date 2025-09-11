package ar.edu.unq.compra_tu_auto.controller.DTO.buyer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BuyerDTO {

    @NotBlank(message = "firstName field is required")
    @Size(min = 2, max = 100, message = "firstName field length must be between 2 and 100 characters.")
    private String firstName;

    @NotBlank(message = "lastName field is required")
    @Size(min = 2, max = 100, message = "lastName field length must be between 2 and 100 characters.")
    private String lastName;

    @NotBlank(message = "email field is required")
    @Size(min = 10, max = 100, message = "email field length must be between 10 and 100 characters.")
    @Email(message = "Email field must be in a valid format")
    private String email;
}