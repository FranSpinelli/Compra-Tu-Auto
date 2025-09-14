package ar.edu.unq.compra_tu_auto.mapper;

import ar.edu.unq.compra_tu_auto.controller.DTO.bookmark.BookmarkRequestDTO;
import ar.edu.unq.compra_tu_auto.controller.DTO.bookmark.BookmarkResponseDTO;
import ar.edu.unq.compra_tu_auto.model.Bookmark;
import ar.edu.unq.compra_tu_auto.repository.sqlRepository.entities.BookmarkEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CarMapper.class, BuyerMapper.class})
public interface BookmarkMapper {

    Bookmark mapFromRequestDTOToModel(BookmarkRequestDTO bookmarkRequestDTO);

    BookmarkEntity mapFromModelToEntity(Bookmark bookmark);

    Bookmark mapFromEntityToModel(BookmarkEntity bookmarkEntity);

    BookmarkResponseDTO mapFromModelToResponseDTO(Bookmark bookmark);
}
