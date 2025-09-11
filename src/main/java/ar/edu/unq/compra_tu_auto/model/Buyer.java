package ar.edu.unq.compra_tu_auto.model;

import jakarta.persistence.OneToMany;
import lombok.Data;
import java.util.Set;

@Data
public class Buyer {

    private Integer buyerId;
    private String firstName;
    private String lastName;
    private String email;

    private Set<Bookmark> bookmarks;
}
