package ar.edu.unq.compra_tu_auto.repository.sqlRepository.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PurchaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private BuyerEntity buyerId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private CarEntity carId;

    private Boolean deleted;
}
