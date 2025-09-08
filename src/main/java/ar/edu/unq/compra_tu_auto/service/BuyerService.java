package ar.edu.unq.compra_tu_auto.service;

import ar.edu.unq.compra_tu_auto.controller.DTO.BuyerDTO;
import ar.edu.unq.compra_tu_auto.model.Buyer;

import java.util.Optional;

public interface BuyerService {

    Buyer createBuyer(BuyerDTO buyerDTO);

    Buyer updateBuyer(Integer buyerId, BuyerDTO buyerDTO);

    Optional<Buyer> getBuyerWithId(Integer buyerId);

    void deleteBuyer(Integer buyerId);
}
