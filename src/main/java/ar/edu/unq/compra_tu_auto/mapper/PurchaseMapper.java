package ar.edu.unq.compra_tu_auto.mapper;

import ar.edu.unq.compra_tu_auto.controller.DTO.purchase.PurchaseResponseDTO;
import ar.edu.unq.compra_tu_auto.model.Purchase;
import ar.edu.unq.compra_tu_auto.repository.sqlRepository.entities.PurchaseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CarMapper.class, BuyerMapper.class})
public interface PurchaseMapper {

    @Mapping(source = "buyer", target = "buyerId")
    @Mapping(source = "car", target = "carId")
    PurchaseEntity mapFromModelToEntity(Purchase purchase);

    @Mapping(source = "buyerId", target = "buyer")
    @Mapping(source = "carId", target = "car")
    Purchase mapFromEntityToModel(PurchaseEntity purchaseEntity);

    @Mapping(source = "buyer", target = "buyer")
    @Mapping(source = "car", target = "car")
    PurchaseResponseDTO mapFromModelToResponseDTO(Purchase purchase);
}
