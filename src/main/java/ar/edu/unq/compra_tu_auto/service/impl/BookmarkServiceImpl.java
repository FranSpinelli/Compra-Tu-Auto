package ar.edu.unq.compra_tu_auto.service.impl;

import ar.edu.unq.compra_tu_auto.controller.DTO.BookmarkDTO;
import ar.edu.unq.compra_tu_auto.exception.ElementNotFoundException;
import ar.edu.unq.compra_tu_auto.mapper.BookmarkMapper;
import ar.edu.unq.compra_tu_auto.model.Bookmark;
import ar.edu.unq.compra_tu_auto.repository.BookmarkRepository;
import ar.edu.unq.compra_tu_auto.service.BookmarkService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class BookmarkServiceImpl implements BookmarkService {

    private final BookmarkMapper bookmarkMapper;
    private final BookmarkRepository bookmarkRepository;

    public BookmarkServiceImpl(BookmarkMapper bookmarkMapper, BookmarkRepository bookmarkRepository) {
        this.bookmarkMapper = bookmarkMapper;
        this.bookmarkRepository = bookmarkRepository;
    }

    @Override
    public Bookmark createBookmark(BookmarkDTO bookmarkDTO) {
        return bookmarkRepository.saveBookmark(bookmarkMapper.mapFromDTOToModel(bookmarkDTO));
    }

    @Override
    public Bookmark updateBookmark(Integer bookmarkId, BookmarkDTO bookmarkDTO) {
        Bookmark foundBookmarkToEdit = bookmarkRepository.getBookmarkWithBookmarkId(bookmarkId)
                .orElseThrow(() -> new ElementNotFoundException("Bookmark", bookmarkId.toString()));

        foundBookmarkToEdit.setScore(bookmarkDTO.getScore());
        foundBookmarkToEdit.setReview(bookmarkDTO.getReview());

        return bookmarkRepository.saveBookmark(foundBookmarkToEdit);
    }

    @Override
    public Optional<Bookmark> getBookmarkWithBookmarkId(Integer bookmarkId) {
        return bookmarkRepository.getBookmarkWithBookmarkId(bookmarkId);
    }

    @Override
    public void deleteBookmark(Integer bookmarkId) {
        bookmarkRepository.deleteBookmarkWithBookmarkId(bookmarkId);
    }
}
