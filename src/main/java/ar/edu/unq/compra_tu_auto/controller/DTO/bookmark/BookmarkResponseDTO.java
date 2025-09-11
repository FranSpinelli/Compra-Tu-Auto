package ar.edu.unq.compra_tu_auto.controller.DTO.bookmark;

import ar.edu.unq.compra_tu_auto.model.Car;
import lombok.Data;

@Data
public class BookmarkResponseDTO {

    private Integer id;
    private Integer score;
    private String review;
    private Car car;
}
