package ar.edu.unq.compra_tu_auto.repository.sqlRepository.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(
        name = "car_models",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"company_name", "model_name"})
        }
)
@Data
public class CarModelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String companyName;

    private String modelName;

    @ElementCollection
    @CollectionTable(
            name = "car_model_manufacturing_years",
            joinColumns = @JoinColumn(name = "car_model_id"),
            uniqueConstraints = @UniqueConstraint(
                    columnNames = {"car_model_id", "manufacturing_year"}
            )
    )
    @Column(name = "manufacturing_year")
    private List<Integer> manufacturingYears;

    @ElementCollection
    @CollectionTable(
            name = "car_model_colors",
            joinColumns = @JoinColumn(name = "car_model_id"),
            uniqueConstraints = @UniqueConstraint(
                    columnNames = {"car_model_id", "color_hexadecimal_code"}
            )
    )
    @Column(name = "color_hexadecimal_code")
    private List<String> colors;
}
