package ar.edu.unq.compra_tu_auto.mapper;

import ar.edu.unq.compra_tu_auto.model.CarModel;
import ar.edu.unq.compra_tu_auto.repository.sqlRepository.entities.CarModelEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarModelMapper {

    CarModel mapFromEntityToModel(CarModelEntity carModelEntity);
}
