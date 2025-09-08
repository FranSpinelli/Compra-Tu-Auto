package ar.edu.unq.compra_tu_auto.repository.impl;

import ar.edu.unq.compra_tu_auto.mapper.BuyerMapper;
import ar.edu.unq.compra_tu_auto.model.Buyer;
import ar.edu.unq.compra_tu_auto.repository.BuyerRepository;
import ar.edu.unq.compra_tu_auto.repository.sqlRepository.BuyerSqlRepository;
import ar.edu.unq.compra_tu_auto.repository.sqlRepository.entities.BuyerEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BuyerRepositoryImpl implements BuyerRepository {

    private final BuyerMapper buyerMapper;
    private final BuyerSqlRepository buyerSqlRepository;

    public BuyerRepositoryImpl(BuyerMapper buyerMapper, BuyerSqlRepository buyerSqlRepository) {
        this.buyerMapper = buyerMapper;
        this.buyerSqlRepository = buyerSqlRepository;
    }

    @Override
    public Buyer saveBuyer(Buyer buyerToBeCreated) {
        BuyerEntity buyerToBeSaved = buyerMapper.mapFromModelToEntity(buyerToBeCreated);
        buyerToBeSaved.setDeleted(false);
        BuyerEntity savedBuyer = buyerSqlRepository.save(buyerToBeSaved);
        return buyerMapper.mapFromEntityToModel(savedBuyer);
    }

    @Override
    public Optional<Buyer> getBuyerWithId(Integer buyerId) {
        return buyerSqlRepository.findById(buyerId)
                .filter(buyerEntity -> !buyerEntity.getDeleted()).map(buyerMapper::mapFromEntityToModel);
    }

    @Override
    public void deleteBuyerById(Integer id) {
        Optional<BuyerEntity> buyerWithId = buyerSqlRepository.findById(id);

        if (buyerWithId.isPresent() && !buyerWithId.get().getDeleted()) {
            BuyerEntity buyerToBeDeleted = buyerWithId.get();
            buyerToBeDeleted.setDeleted(true);
            buyerSqlRepository.save(buyerToBeDeleted);
        }
    }
}
