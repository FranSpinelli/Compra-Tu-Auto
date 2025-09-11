package ar.edu.unq.compra_tu_auto.mapper;

import ar.edu.unq.compra_tu_auto.controller.DTO.BookmarkDTO;
import ar.edu.unq.compra_tu_auto.controller.DTO.BookmarkResponseDTO;
import ar.edu.unq.compra_tu_auto.model.Bookmark;
import ar.edu.unq.compra_tu_auto.repository.sqlRepository.entities.BookmarkEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookmarkMapper {

    BookmarkResponseDTO mapFromModelToDTO(Bookmark bookmark);
    Bookmark mapFromDTOToModel(BookmarkDTO bookmarkDTO);
    BookmarkEntity mapFromModelToEntity(Bookmark bookmark);
    Bookmark mapFromEntityToModel(BookmarkEntity bookmarkEntity);
}
