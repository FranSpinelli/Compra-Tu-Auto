package ar.edu.unq.compra_tu_auto.service;

import ar.edu.unq.compra_tu_auto.controller.DTO.bookmark.BookmarkRequestDTO;
import ar.edu.unq.compra_tu_auto.model.Bookmark;

import java.util.Optional;

public interface BookmarkService {

    Bookmark createBookmark(Integer buyerId, BookmarkRequestDTO bookmarkRequestDTO);

    Bookmark updateBookmark(Integer buyerId, Integer bookmarkId, BookmarkRequestDTO bookmarkRequestDTO);

    Optional<Bookmark> getBookmarkWithBookmarkId(Integer buyerId, Integer bookmarkId);

    void deleteBookmarkById(Integer buyerId, Integer bookmarkId);
}
