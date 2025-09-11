package ar.edu.unq.compra_tu_auto.repository;

import ar.edu.unq.compra_tu_auto.model.Bookmark;

import java.util.Optional;

public interface BookmarkRepository {

    Bookmark saveBookmark(Bookmark bookmark);

    Optional<Bookmark> getBookmarkWithBookmarkId(Integer bookmarkId);

    void deleteBookmarkWithBookmarkId(Integer bookmarkId);
}
