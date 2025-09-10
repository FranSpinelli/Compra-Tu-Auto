package ar.edu.unq.compra_tu_auto.repository.sqlRepository.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
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
