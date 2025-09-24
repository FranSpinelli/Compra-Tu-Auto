package ar.edu.unq.compra_tu_auto.controller.DTO.car;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class CarRequestDTO {

    @NotNull(message = "Car Model ID field is required.")
    @Positive(message = "Car Model ID field must be greater than 0.")
    private Integer carModelId;

    @NotBlank(message = "Color field is required")
    @Size(min = 2, max = 100, message = "Color field length must be between 2 and 100 characters.")
    private String color;

    @NotNull(message = "Manufacturing Year field is required.")
    @Positive(message = "Manufacturing Year field must be greater than 0.")
    @Min(value = 1900, message = "Manufacturing Year field must be greater than 1900.")
    private Integer manufacturingYear;

    @NotNull(message = "Price field is required.")
    @Positive(message = "Price field must be greater than 0.")
    private Double price;
}
