package ar.edu.unq.compra_tu_auto.model;

import lombok.Data;

import java.util.List;

@Data
public class CarModel {

    private Integer id;
    private String companyName;
    private String modelName;
    private List<Integer> manufacturingYears;
    private List<String> colors;
}
