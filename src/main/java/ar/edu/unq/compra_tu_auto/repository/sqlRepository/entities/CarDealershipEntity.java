package ar.edu.unq.compra_tu_auto.repository.sqlRepository.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
