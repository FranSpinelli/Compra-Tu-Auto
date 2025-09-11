package ar.edu.unq.compra_tu_auto.service;

import ar.edu.unq.compra_tu_auto.controller.DTO.buyer.BuyerRequestDTO;
import ar.edu.unq.compra_tu_auto.model.Buyer;

import java.util.Optional;

public interface BuyerService {

    Buyer createBuyer(BuyerRequestDTO buyerDTO);

    Buyer updateBuyer(Integer buyerId, BuyerRequestDTO buyerDTO);

    Optional<Buyer> getBuyerWithId(Integer buyerId);

    void deleteBuyer(Integer buyerId);
}
