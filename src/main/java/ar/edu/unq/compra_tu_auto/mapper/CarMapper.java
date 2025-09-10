package ar.edu.unq.compra_tu_auto.mapper;

import ar.edu.unq.compra_tu_auto.controller.DTO.CarDTO;
import ar.edu.unq.compra_tu_auto.controller.DTO.CarResponseDTO;
import ar.edu.unq.compra_tu_auto.model.Car;
import ar.edu.unq.compra_tu_auto.repository.sqlRepository.entities.CarEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CarMapper {

    Car mapFromDtoToModel(CarDTO carDto);

    @Mapping(source = "dealershipId", target = "carDealership.id")
    CarEntity mapFromModelToEntity(Car car);

    @Mapping(source = "carDealership.id", target = "dealershipId")
    Car mapFromEntityToModel(CarEntity carEntity);

    CarResponseDTO mapFromModelToDto(Car car);
}
