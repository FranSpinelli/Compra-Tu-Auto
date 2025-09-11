package ar.edu.unq.compra_tu_auto.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Purchase {

    private Integer id;

    private Buyer buyer;

    private Car car;

    private Boolean deleted;
}
