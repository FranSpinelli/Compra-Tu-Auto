package ar.edu.unq.compra_tu_auto.repository.impl;

import ar.edu.unq.compra_tu_auto.mapper.BookmarkMapper;
import ar.edu.unq.compra_tu_auto.model.Bookmark;
import ar.edu.unq.compra_tu_auto.repository.BookmarkRepository;
import ar.edu.unq.compra_tu_auto.repository.sqlRepository.BookmarkSqlRepository;
import ar.edu.unq.compra_tu_auto.repository.sqlRepository.entities.BookmarkEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookmarkRepositoryImpl implements BookmarkRepository {

    private final BookmarkMapper bookmarkMapper;
    private final BookmarkSqlRepository bookmarkSqlRepository;

    public BookmarkRepositoryImpl(BookmarkMapper bookmarkMapper, BookmarkSqlRepository bookmarkSqlRepository) {
        this.bookmarkMapper = bookmarkMapper;
        this.bookmarkSqlRepository = bookmarkSqlRepository;
    }

    @Override
    public Bookmark saveBookmark(Bookmark bookmark) {
        BookmarkEntity bookmarkToBeSaved = bookmarkMapper.mapFromModelToEntity(bookmark);
        bookmarkToBeSaved.setDeleted(false);
        BookmarkEntity savedBookmark = bookmarkSqlRepository.save(bookmarkToBeSaved);
        return bookmarkMapper.mapFromEntityToModel(savedBookmark);
    }

    @Override
    public Optional<Bookmark> getBookmarkWithBookmarkId(Integer bookmarkId) {
        return bookmarkSqlRepository.findById(bookmarkId)
                .filter(bookmarkEntity -> !bookmarkEntity.getDeleted())
                .map(bookmarkMapper::mapFromEntityToModel);
    }

    @Override
    public void deleteBookmarkWithBookmarkId(Integer bookmarkId) {
        Optional<BookmarkEntity> bookmarkWithId = bookmarkSqlRepository.findById(bookmarkId);

        if (bookmarkWithId.isPresent() && !bookmarkWithId.get().getDeleted()) {
            BookmarkEntity bookmarkToBeDeleted = bookmarkWithId.get();
            bookmarkToBeDeleted.setDeleted(true);
            bookmarkSqlRepository.save(bookmarkToBeDeleted);
        }
    }
}
