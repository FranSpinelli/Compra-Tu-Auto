package ar.edu.unq.compra_tu_auto.controller.DTO.bookmark;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class BookmarkRequestDTO {

    @NotNull
    @Positive
    @Min(0)
    @Max(10)
    private Integer score;

    @NotNull
    private String review;
}
