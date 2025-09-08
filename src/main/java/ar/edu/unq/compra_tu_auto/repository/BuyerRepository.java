package ar.edu.unq.compra_tu_auto.repository;

import ar.edu.unq.compra_tu_auto.model.Buyer;
import java.util.Optional;

public interface BuyerRepository {

    Buyer saveBuyer(Buyer buyer);

    Optional<Buyer> getBuyerWithId(Integer id);

    void deleteBuyerById(Integer id);
}
