package ar.edu.unq.compra_tu_auto.controller.DTO.bookmark;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class BookmarkRequestDTO {

    @Min(value = 0, message = "Score must be between 0 and 10.")
    @Max(value = 10, message = "Score must be between 0 and 10.")
    private Integer score;

    @NotBlank(message = "review field is required")
    private String review;

    @NotNull(message = "Car Dealership Id field is required")
    private Integer carDealershipId;

    @NotNull(message = "Car Id field is required")
    private Integer carId;
}
