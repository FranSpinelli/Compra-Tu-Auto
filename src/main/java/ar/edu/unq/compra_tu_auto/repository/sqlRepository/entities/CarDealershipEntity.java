package ar.edu.unq.compra_tu_auto.repository.sqlRepository.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class CarDealershipEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String email;

    @OneToMany(mappedBy = "carDealership", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarEntity> cars;

    private Boolean deleted;
}
