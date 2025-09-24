package ar.edu.unq.compra_tu_auto.model;

import lombok.Data;

@Data
public class Purchase {

    private Integer id;
    private Double purchaseValue;
    private String carCompanyName;
    private String carModelName;
    private Integer carManufacturingYear;
    private String carColor;
    private String licensePlate;
    private Boolean deleted;
}
