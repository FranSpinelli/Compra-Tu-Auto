package ar.edu.unq.compra_tu_auto.repository.sqlRepository.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class BookmarkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer score;
    private String review;
    private Boolean deleted;

    @ManyToOne
    private CarEntity car;

    @ManyToOne
    private BuyerEntity buyer;
}
