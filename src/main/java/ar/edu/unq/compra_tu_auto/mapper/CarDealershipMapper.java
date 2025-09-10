package ar.edu.unq.compra_tu_auto.mapper;

import ar.edu.unq.compra_tu_auto.controller.DTO.carDealership.CarDealershipRequestDTO;
import ar.edu.unq.compra_tu_auto.controller.DTO.carDealership.CarDealershipResponseDTO;
import ar.edu.unq.compra_tu_auto.model.CarDealership;
import ar.edu.unq.compra_tu_auto.repository.sqlRepository.entities.CarDealershipEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = CarMapper.class)
public interface CarDealershipMapper {

    CarDealership mapFromDtoToModel(CarDealershipRequestDTO carDealershipRequestDTO);
    CarDealershipEntity mapFromModelToEntity(CarDealership carDealership);
    CarDealership mapFromEntityToModel(CarDealershipEntity savedCarDealership);
    CarDealershipResponseDTO mapFromModelToDto(CarDealership createdCarDealership);
}
