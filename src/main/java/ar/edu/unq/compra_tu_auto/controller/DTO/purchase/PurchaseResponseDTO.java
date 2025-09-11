package ar.edu.unq.compra_tu_auto.controller.DTO.purchase;

import ar.edu.unq.compra_tu_auto.controller.DTO.buyer.BuyerResponseDTO;
import ar.edu.unq.compra_tu_auto.controller.DTO.car.CarResponseDTO;
import lombok.Data;

@Data
public class PurchaseResponseDTO {

    private Integer id;

    private BuyerResponseDTO buyer;

    private CarResponseDTO car;
}
