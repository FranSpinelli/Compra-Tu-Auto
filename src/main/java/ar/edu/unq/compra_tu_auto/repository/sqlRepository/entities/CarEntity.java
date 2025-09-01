package ar.edu.unq.compra_tu_auto.repository.sqlRepository.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;
    private String brand;
    private String model;
    private String color;
    private Integer manufactureYear;
    private Integer stock;
    private Double price;
    private String description;
    private Boolean deleted;
}
