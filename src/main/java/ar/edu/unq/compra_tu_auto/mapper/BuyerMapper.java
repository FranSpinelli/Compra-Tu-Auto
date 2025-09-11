package ar.edu.unq.compra_tu_auto.mapper;

import ar.edu.unq.compra_tu_auto.controller.DTO.buyer.BuyerDTO;
import ar.edu.unq.compra_tu_auto.controller.DTO.buyer.BuyerResponseDTO;
import ar.edu.unq.compra_tu_auto.model.Buyer;
import ar.edu.unq.compra_tu_auto.repository.sqlRepository.entities.BuyerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BuyerMapper {

    Buyer mapFromRequestDTOToModel(BuyerDTO buyerDTO);

    BuyerEntity mapFromModelToEntity(Buyer buyer);

    Buyer mapFromEntityToModel(BuyerEntity buyerEntity);

    BuyerResponseDTO mapFromModelToResponseDTO(Buyer buyer);
}
