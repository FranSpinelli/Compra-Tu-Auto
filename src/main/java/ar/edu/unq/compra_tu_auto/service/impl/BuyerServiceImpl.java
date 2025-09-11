package ar.edu.unq.compra_tu_auto.service.impl;

import ar.edu.unq.compra_tu_auto.controller.DTO.buyer.BuyerRequestDTO;
import ar.edu.unq.compra_tu_auto.exception.ElementNotFoundException;
import ar.edu.unq.compra_tu_auto.mapper.BuyerMapper;
import ar.edu.unq.compra_tu_auto.model.Buyer;
import ar.edu.unq.compra_tu_auto.repository.BuyerRepository;
import ar.edu.unq.compra_tu_auto.service.BuyerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuyerServiceImpl implements BuyerService {

    private final BuyerMapper buyerMapper;
    private final BuyerRepository buyerRepository;

    public BuyerServiceImpl(BuyerMapper buyerMapper, BuyerRepository buyerRepository) {
        this.buyerMapper = buyerMapper;
        this.buyerRepository = buyerRepository;
    }

    @Override
<<<<<<< HEAD
    public Buyer createBuyer(BuyerRequestDTO buyerDTO) {
        return buyerRepository.saveBuyer(buyerMapper.mapFromDTOToModel(buyerDTO));
=======
    public Buyer createBuyer(BuyerDTO buyerDTO) {
        return buyerRepository.saveBuyer(buyerMapper.mapFromRequestDTOToModel(buyerDTO));
>>>>>>> develop
    }

    @Override
    public Buyer updateBuyer(Integer buyerId, BuyerRequestDTO buyerDTO) {
        Buyer foundBuyerToEdit = buyerRepository.getBuyerWithId(buyerId)
                .orElseThrow(() -> new ElementNotFoundException("Buyer", buyerId.toString()));

        foundBuyerToEdit.setFirstName(buyerDTO.getFirstName());
        foundBuyerToEdit.setLastName(buyerDTO.getLastName());
        foundBuyerToEdit.setEmail(buyerDTO.getEmail());

        return buyerRepository.saveBuyer(foundBuyerToEdit);
    }

    @Override
    public Optional<Buyer> getBuyerWithId(Integer buyerId) {
        return buyerRepository.getBuyerWithId(buyerId);
    }

    @Override
    public void deleteBuyer(Integer buyerId) {
        buyerRepository.deleteBuyerById(buyerId);
    }
}
