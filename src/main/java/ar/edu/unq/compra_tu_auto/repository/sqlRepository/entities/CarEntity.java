package ar.edu.unq.compra_tu_auto.repository.sqlRepository.entities;


import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "cars")
@Data
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String brand;

    private String model;

    private String color;

    private Integer manufactureYear;

    private Integer stock;

    private Double price;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private CarDealershipEntity carDealership;

    private Boolean deleted;
}
