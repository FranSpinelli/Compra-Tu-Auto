package ar.edu.unq.compra_tu_auto.controller.DTO.car;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CarRequestDTO {

    @NotBlank(message = "Brand field is required")
    @Size(min = 2, max = 100, message = "Brand field length must be between 2 and 100 characters.")
    private String brand;

    @NotBlank(message = "Model field is required")
    @Size(min = 2, max = 100, message = "Model field length must be between 2 and 100 characters.")
    private String model;

    @NotBlank(message = "Color field is required")
    @Size(min = 2, max = 100, message = "Color field length must be between 2 and 100 characters.")
    private String color;

    @NotNull(message = "Manufacture Year field is required.")
    @Positive(message = "Manufacture Year field must be greater than 0.")
    @Min(value = 1900, message = "Manufacture Year field must be greater than 1900.")
    private Integer manufactureYear;

    @NotNull(message = "Stock field is required.")
    @Positive(message = "Stock field must be greater than 0.")
    private Integer stock;

    @NotNull(message = "Price field is required.")
    @Positive(message = "Price field must be greater than 0.")
    private Double price;

    @NotBlank(message = "Description field is required")
    @Size(min = 2, max = 150, message = "Description field length must be between 2 and 150 characters.")
    private String description;
}
