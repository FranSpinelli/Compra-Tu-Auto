package ar.edu.unq.compra_tu_auto.mapper;

import ar.edu.unq.compra_tu_auto.controller.DTO.buyer.BuyerRequestDTO;
import ar.edu.unq.compra_tu_auto.controller.DTO.buyer.BuyerResponseDTO;
import ar.edu.unq.compra_tu_auto.model.Buyer;
import ar.edu.unq.compra_tu_auto.repository.sqlRepository.entities.BuyerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BuyerMapper {

    BuyerResponseDTO mapFromModelToDTO(Buyer buyer);
    Buyer mapFromDTOToModel(BuyerRequestDTO buyerDTO);
    BuyerEntity mapFromModelToEntity(Buyer buyer);
    Buyer mapFromEntityToModel(BuyerEntity buyerEntity);
}
