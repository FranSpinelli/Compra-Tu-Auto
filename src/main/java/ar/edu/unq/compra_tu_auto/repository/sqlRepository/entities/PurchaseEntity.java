package ar.edu.unq.compra_tu_auto.repository.sqlRepository.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "purchases")
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
