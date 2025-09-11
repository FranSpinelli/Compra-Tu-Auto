package ar.edu.unq.compra_tu_auto.model;

import lombok.Data;

@Data
public class Bookmark {

    private Integer id;
    private Integer score;
    private String review;
    private Car car;
    private Buyer buyer;
}
