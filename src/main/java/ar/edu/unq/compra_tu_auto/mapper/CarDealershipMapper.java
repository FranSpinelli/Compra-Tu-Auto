package ar.edu.unq.compra_tu_auto.mapper;

import ar.edu.unq.compra_tu_auto.controller.DTO.CarDealershipDTO;
import ar.edu.unq.compra_tu_auto.controller.DTO.CarDealershipResponseDTO;
import ar.edu.unq.compra_tu_auto.model.CarDealership;
import ar.edu.unq.compra_tu_auto.repository.sqlRepository.entities.CarDealershipEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarDealershipMapper {

    CarDealershipResponseDTO mapFromModelToDto(CarDealership createdCarDealership);
    CarDealership mapFromDtoToModel(CarDealershipDTO carDealershipDTO);
    CarDealershipEntity mapFromModelToEntity(CarDealership carDealership);
    CarDealership mapFromEntityToModel(CarDealershipEntity savedCarDealership);
}
