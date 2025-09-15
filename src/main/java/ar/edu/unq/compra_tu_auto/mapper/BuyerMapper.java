package ar.edu.unq.compra_tu_auto.mapper;

import ar.edu.unq.compra_tu_auto.controller.DTO.buyer.BuyerRequestDTO;
import ar.edu.unq.compra_tu_auto.controller.DTO.buyer.BuyerResponseDTO;
import ar.edu.unq.compra_tu_auto.model.Buyer;
import ar.edu.unq.compra_tu_auto.repository.sqlRepository.entities.BuyerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = BookmarkMapper.class)
public interface BuyerMapper {

    Buyer mapFromRequestDTOToModel(BuyerRequestDTO buyerDTO);

    BuyerEntity mapFromModelToEntity(Buyer buyer);

    Buyer mapFromEntityToModel(BuyerEntity buyerEntity);

    BuyerResponseDTO mapFromModelToResponseDTO(Buyer buyer);
}
