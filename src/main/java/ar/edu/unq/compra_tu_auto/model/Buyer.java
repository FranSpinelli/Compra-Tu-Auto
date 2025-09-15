package ar.edu.unq.compra_tu_auto.model;

import lombok.Data;

import java.util.List;

@Data
public class Buyer {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private List<Bookmark> bookmarks;
}
