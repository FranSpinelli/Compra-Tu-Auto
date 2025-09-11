package ar.edu.unq.compra_tu_auto.repository.sqlRepository.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class BuyerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean deleted;

    @OneToMany(mappedBy = "buyer")
    private Set<BookmarkEntity> bookmarks;
}
