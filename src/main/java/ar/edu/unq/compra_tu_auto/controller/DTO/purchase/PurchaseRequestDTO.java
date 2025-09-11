package ar.edu.unq.compra_tu_auto.controller.DTO.purchase;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PurchaseRequestDTO {

    @NotNull(message = "Car Id field is required.")
    private Integer carId;

    @NotNull(message = "Car Dealership Id field is required.")
    private Integer carDealershipId;

    @NotNull(message = "Buyer Id field is required.")
    private Integer buyerId;
}
