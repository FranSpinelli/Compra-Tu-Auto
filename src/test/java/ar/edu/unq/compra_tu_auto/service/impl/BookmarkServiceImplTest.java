package ar.edu.unq.compra_tu_auto.service.impl;

import ar.edu.unq.compra_tu_auto.controller.DTO.bookmark.BookmarkRequestDTO;
import ar.edu.unq.compra_tu_auto.exception.ElementNotFoundException;
import ar.edu.unq.compra_tu_auto.mapper.BookmarkMapper;
import ar.edu.unq.compra_tu_auto.model.Bookmark;
import ar.edu.unq.compra_tu_auto.repository.BookmarkRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookmarkServiceImplTest {

    @InjectMocks
    private BookmarkServiceImpl bookmarkServiceImpl;

    @Mock
    private BookmarkRepository bookmarkRepository;

    @Mock
    private BookmarkMapper bookmarkMapper;

    @Test
    public void createBookmarkTest(){
        BookmarkRequestDTO bookmarkRequestDTO = mock(BookmarkRequestDTO.class);
        Bookmark mockBookmark = mock(Bookmark.class);
        when(bookmarkMapper.mapFromDTOToModel(eq(bookmarkRequestDTO))).thenReturn(mockBookmark);
        when(bookmarkRepository.saveBookmark(mockBookmark)).thenReturn(mockBookmark);

        Bookmark result = bookmarkServiceImpl.createBookmark(bookmarkRequestDTO);

        assertEquals(mockBookmark, result);
        verify(bookmarkRepository, times(1)).saveBookmark(eq(mockBookmark));
        verify(bookmarkMapper, times(1)).mapFromDTOToModel(eq(bookmarkRequestDTO));
    }

    @Test
    public void updateBookmarkTest(){
        Integer bookmarkId = 1;
        Integer expectedScore = 3;
        String expectedReview = "Esta es una review de un auto";

        BookmarkRequestDTO bookmarkRequestDTO = new BookmarkRequestDTO();
        bookmarkRequestDTO.setScore(expectedScore);
        bookmarkRequestDTO.setReview(expectedReview);

        Bookmark foundBookmark = mock(Bookmark.class);
        Bookmark updatedBookmark = mock(Bookmark.class);

        when(bookmarkRepository.getBookmarkWithBookmarkId(eq(bookmarkId))).thenReturn(Optional.of(foundBookmark));
        when(bookmarkRepository.saveBookmark(any(Bookmark.class))).thenReturn(updatedBookmark);

        Bookmark result = bookmarkServiceImpl.updateBookmark(bookmarkId, bookmarkRequestDTO);

        assertEquals(updatedBookmark, result);
        verify(bookmarkRepository, times(1)).getBookmarkWithBookmarkId(eq(bookmarkId));
        verify(foundBookmark, times(1)).setScore(eq(expectedScore));
        verify(foundBookmark, times(1)).setReview(eq(expectedReview));
        verify(bookmarkRepository, times(1)).saveBookmark(eq(foundBookmark));
    }

    @Test
    public void updateBookmarkNotFoundTest(){
        Integer bookmarkId = 1;
        BookmarkRequestDTO bookmarkRequestDTO = new BookmarkRequestDTO();
        when(bookmarkRepository.getBookmarkWithBookmarkId(eq(bookmarkId))).thenReturn(Optional.empty());

        ElementNotFoundException exception = assertThrows(ElementNotFoundException.class,
                () -> bookmarkServiceImpl.updateBookmark(bookmarkId, bookmarkRequestDTO));

        assertEquals("Bookmark with Id: " + bookmarkId + " not found", exception.getMessage());
        verify(bookmarkRepository, times(1)).getBookmarkWithBookmarkId(eq(bookmarkId));
        verify(bookmarkRepository, never()).saveBookmark(any(Bookmark.class));
    }

    @Test
    public void getBookmarkByIdTest(){
        Integer bookmarkId = 1;
        Bookmark mockBookmark = mock(Bookmark.class);
        when(bookmarkRepository.getBookmarkWithBookmarkId(eq(bookmarkId))).thenReturn(Optional.of(mockBookmark));

        Optional<Bookmark> result = bookmarkServiceImpl.getBookmarkWithBookmarkId(bookmarkId);

        assertEquals(mockBookmark, result.get());
        verify(bookmarkRepository, times(1)).getBookmarkWithBookmarkId(eq(bookmarkId));
    }

    @Test
    public void deleteBookmarkByIdTest(){
        Integer bookmarkId = 1;

        bookmarkServiceImpl.deleteBookmarkById(bookmarkId);

        verify(bookmarkRepository, times(1)).deleteBookmarkWithBookmarkId(eq(bookmarkId));
    }
}
