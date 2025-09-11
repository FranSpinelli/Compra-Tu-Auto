package ar.edu.unq.compra_tu_auto.service;

import ar.edu.unq.compra_tu_auto.controller.DTO.BookmarkDTO;
import ar.edu.unq.compra_tu_auto.model.Bookmark;

import java.util.Optional;

public interface BookmarkService {

    Bookmark createBookmark(BookmarkDTO bookmarkDTO);

    Bookmark updateBookmark(Integer bookmarkId, BookmarkDTO bookmarkDTO);

    Optional<Bookmark> getBookmarkWithBookmarkId(Integer bookmarkId);

    void deleteBookmark(Integer bookmarkId);
}
