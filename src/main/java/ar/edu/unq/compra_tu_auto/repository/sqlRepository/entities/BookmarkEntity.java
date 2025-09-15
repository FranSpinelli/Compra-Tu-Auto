package ar.edu.unq.compra_tu_auto.repository.sqlRepository.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import lombok.Data;

@Entity
@Data
public class BookmarkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer score;
    private String review;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private CarEntity car;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private BuyerEntity buyer;

    private Boolean deleted;
}
