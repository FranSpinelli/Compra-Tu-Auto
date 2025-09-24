package ar.edu.unq.compra_tu_auto.repository.sqlRepository.entities;


import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Entity
@Table(name = "cars")
@Data
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "car_model_id", nullable = false)
    private CarModelEntity carModel;

    private String color;

    private Integer manufacturingYear;

    private Double price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private CarDealershipEntity carDealership;

    private Boolean deleted;
}
