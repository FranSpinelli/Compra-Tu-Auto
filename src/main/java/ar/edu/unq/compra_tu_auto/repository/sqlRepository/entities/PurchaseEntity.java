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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double purchaseValue;

    private String carCompanyName;

    private String carModelName;

    private Integer carManufacturingYear;

    private String carColor;

    private String licensePlate;

    private Boolean deleted;
}
