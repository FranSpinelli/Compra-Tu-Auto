package ar.edu.unq.compra_tu_auto.service.impl;

import ar.edu.unq.compra_tu_auto.controller.DTO.bookmark.BookmarkRequestDTO;
import ar.edu.unq.compra_tu_auto.exception.ElementNotFoundException;
import ar.edu.unq.compra_tu_auto.mapper.BookmarkMapper;
import ar.edu.unq.compra_tu_auto.model.Bookmark;
import ar.edu.unq.compra_tu_auto.repository.BookmarkRepository;
import ar.edu.unq.compra_tu_auto.service.BookmarkService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookmarkServiceImpl implements BookmarkService {

    private final BookmarkMapper bookmarkMapper;
    private final BookmarkRepository bookmarkRepository;

    public BookmarkServiceImpl(BookmarkMapper bookmarkMapper, BookmarkRepository bookmarkRepository) {
        this.bookmarkMapper = bookmarkMapper;
        this.bookmarkRepository = bookmarkRepository;
    }

    @Override
    public Bookmark createBookmark(BookmarkRequestDTO bookmarkRequestDTO) {
        return bookmarkRepository.saveBookmark(bookmarkMapper.mapFromDTOToModel(bookmarkRequestDTO));
    }

    @Override
    public Bookmark updateBookmark(Integer bookmarkId, BookmarkRequestDTO bookmarkRequestDTO) {
        Bookmark foundBookmarkToEdit = bookmarkRepository.getBookmarkWithBookmarkId(bookmarkId)
                .orElseThrow(() -> new ElementNotFoundException("Bookmark", bookmarkId.toString()));

        foundBookmarkToEdit.setScore(bookmarkRequestDTO.getScore());
        foundBookmarkToEdit.setReview(bookmarkRequestDTO.getReview());

        return bookmarkRepository.saveBookmark(foundBookmarkToEdit);
    }

    @Override
    public Optional<Bookmark> getBookmarkWithBookmarkId(Integer bookmarkId) {
        return bookmarkRepository.getBookmarkWithBookmarkId(bookmarkId);
    }

    @Override
    public void deleteBookmarkById(Integer bookmarkId) {
        bookmarkRepository.deleteBookmarkWithBookmarkId(bookmarkId);
    }
}
