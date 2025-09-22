package ar.edu.unq.compra_tu_auto.repository.sqlRepository.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "car_models")
public class CarModelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String companyName;

    private String modelName;

    @ElementCollection
    @CollectionTable(name = "car_model_manufacturing_years", joinColumns = @JoinColumn(name = "car_model_id"))
    @Column(name = "manufacturing_year")
    private List<Integer> manufacturingYears;

    @ElementCollection
    @CollectionTable(name = "car_model_colors", joinColumns = @JoinColumn(name = "car_model_id"))
    @Column(name = "color_hexadecimal_code")
    private List<String> colors;
}
