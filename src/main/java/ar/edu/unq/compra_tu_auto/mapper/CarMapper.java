package ar.edu.unq.compra_tu_auto.mapper;

import ar.edu.unq.compra_tu_auto.controller.DTO.CarDTO;
import ar.edu.unq.compra_tu_auto.controller.DTO.CarResponseDTO;
import ar.edu.unq.compra_tu_auto.model.Car;
import ar.edu.unq.compra_tu_auto.repository.sqlRepository.entities.CarEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {

    CarResponseDTO mapFromModelToDto(Car car);
    Car mapFromDtoToModel(CarDTO carDto);
    CarEntity mapFromModelToEntity(Car car);
    Car mapFromEntityToModel(CarEntity carEntity);
}
