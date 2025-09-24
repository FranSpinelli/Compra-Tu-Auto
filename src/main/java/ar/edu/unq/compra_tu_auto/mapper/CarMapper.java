package ar.edu.unq.compra_tu_auto.mapper;

import ar.edu.unq.compra_tu_auto.controller.DTO.car.CarRequestDTO;
import ar.edu.unq.compra_tu_auto.controller.DTO.car.CarResponseDTO;
import ar.edu.unq.compra_tu_auto.model.Car;
import ar.edu.unq.compra_tu_auto.repository.sqlRepository.entities.CarEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CarMapper {

    Car mapFromRequestDtoToModel(CarRequestDTO carRequestDto);

    @Mapping(source = "carModelId", target = "carModel.id")
    @Mapping(source = "dealershipId", target = "carDealership.id")
    CarEntity mapFromModelToEntity(Car car);

    @Mapping(source = "carModel.id", target = "carModelId")
    @Mapping(source = "carDealership.id", target = "dealershipId")
    Car mapFromEntityToModel(CarEntity carEntity);

    CarResponseDTO mapFromModelToResponseDto(Car car);

    CarRequestDTO mapFromModelToRequestDto(Car car);
}
