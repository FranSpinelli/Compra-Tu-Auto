package ar.edu.unq.compra_tu_auto.service.impl;

import ar.edu.unq.compra_tu_auto.controller.DTO.bookmark.BookmarkRequestDTO;
import ar.edu.unq.compra_tu_auto.exception.ElementNotFoundException;
import ar.edu.unq.compra_tu_auto.mapper.BookmarkMapper;
import ar.edu.unq.compra_tu_auto.model.Bookmark;
import ar.edu.unq.compra_tu_auto.model.Buyer;
import ar.edu.unq.compra_tu_auto.model.Car;
import ar.edu.unq.compra_tu_auto.repository.BookmarkRepository;
import ar.edu.unq.compra_tu_auto.service.BuyerService;
import ar.edu.unq.compra_tu_auto.service.CarService;
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

    @Mock
    private BuyerService buyerService;

    @Mock
    private CarService carService;

    @Test
    public void createBookmarkTest(){
        Integer buyerId = 1;

        BookmarkRequestDTO bookmarkRequestDTO = mock(BookmarkRequestDTO.class);
        Bookmark mockBookmark = mock(Bookmark.class);
        when(buyerService.getBuyerWithId(eq(buyerId))).thenReturn(Optional.of(mock(Buyer.class)));
        when(carService.getCarWithId(any(), any())).thenReturn(Optional.of(mock(Car.class)));
        when(bookmarkMapper.mapFromRequestDTOToModel(eq(bookmarkRequestDTO))).thenReturn(mockBookmark);
        when(bookmarkRepository.saveBookmark(mockBookmark)).thenReturn(mockBookmark);

        Bookmark result = bookmarkServiceImpl.createBookmark(buyerId, bookmarkRequestDTO);

        assertEquals(mockBookmark, result);
        verify(bookmarkRepository, times(1)).saveBookmark(eq(mockBookmark));
        verify(bookmarkMapper, times(1)).mapFromRequestDTOToModel(eq(bookmarkRequestDTO));
    }

    @Test
    public void createBookmarkOfInexistentCarTest(){
        Integer buyerId = 1;
        BookmarkRequestDTO bookmarkRequestDTO = mock(BookmarkRequestDTO.class);
        when(bookmarkRequestDTO.getCarId()).thenReturn(1);
        when(bookmarkRequestDTO.getCarDealershipId()).thenReturn(1);

        when(buyerService.getBuyerWithId(eq(buyerId))).thenReturn(Optional.of(mock(Buyer.class)));
        when(carService.getCarWithId(eq(1), eq(1))).thenReturn(Optional.empty());

        ElementNotFoundException exception = assertThrows(ElementNotFoundException.class, () -> bookmarkServiceImpl.createBookmark(buyerId, bookmarkRequestDTO));

        assertEquals("Car with Id: " + 1 + " not found", exception.getMessage());
    }

    @Test
    public void updateBookmarkTest(){
        Integer buyerId = 1;
        Integer bookmarkId = 1;
        Integer expectedScore = 3;
        String expectedReview = "Esta es una review de un auto";

        BookmarkRequestDTO bookmarkRequestDTO = new BookmarkRequestDTO();
        bookmarkRequestDTO.setScore(expectedScore);
        bookmarkRequestDTO.setReview(expectedReview);

        Bookmark foundBookmark = mock(Bookmark.class);
        Bookmark updatedBookmark = mock(Bookmark.class);

        when(buyerService.getBuyerWithId(eq(buyerId))).thenReturn(Optional.of(mock(Buyer.class)));
        when(bookmarkRepository.getBookmarkWithBookmarkId(eq(bookmarkId))).thenReturn(Optional.of(foundBookmark));
        when(bookmarkRepository.saveBookmark(any(Bookmark.class))).thenReturn(updatedBookmark);

        Bookmark result = bookmarkServiceImpl.updateBookmark(buyerId, bookmarkId, bookmarkRequestDTO);

        assertEquals(updatedBookmark, result);
        verify(bookmarkRepository, times(1)).getBookmarkWithBookmarkId(eq(bookmarkId));
        verify(foundBookmark, times(1)).setScore(eq(expectedScore));
        verify(foundBookmark, times(1)).setReview(eq(expectedReview));
        verify(bookmarkRepository, times(1)).saveBookmark(eq(foundBookmark));
    }

    @Test
    public void updateInexistentBookmarkNotFoundTest(){
        Integer bookmarkId = 1;
        Integer buyerId = 1;

        when(buyerService.getBuyerWithId(eq(buyerId))).thenReturn(Optional.of(mock(Buyer.class)));
        BookmarkRequestDTO bookmarkRequestDTO = new BookmarkRequestDTO();
        when(bookmarkRepository.getBookmarkWithBookmarkId(eq(bookmarkId))).thenReturn(Optional.empty());

        ElementNotFoundException exception = assertThrows(ElementNotFoundException.class,
                () -> bookmarkServiceImpl.updateBookmark(buyerId, bookmarkId, bookmarkRequestDTO));

        assertEquals("Bookmark with Id: " + bookmarkId + " not found", exception.getMessage());
        verify(bookmarkRepository, times(1)).getBookmarkWithBookmarkId(eq(bookmarkId));
        verify(bookmarkRepository, never()).saveBookmark(any(Bookmark.class));
    }

    @Test
    public void getBookmarkByIdTest(){
        Integer bookmarkId = 1;
        Integer buyerId = 1;

        when(buyerService.getBuyerWithId(eq(buyerId))).thenReturn(Optional.of(mock(Buyer.class)));
        Bookmark mockBookmark = mock(Bookmark.class);
        when(bookmarkRepository.getBookmarkWithBookmarkId(eq(bookmarkId))).thenReturn(Optional.of(mockBookmark));

        Optional<Bookmark> result = bookmarkServiceImpl.getBookmarkWithBookmarkId(buyerId, bookmarkId);

        assertEquals(mockBookmark, result.get());
        verify(bookmarkRepository, times(1)).getBookmarkWithBookmarkId(eq(bookmarkId));
    }

    @Test
    public void deleteBookmarkByIdTest(){
        Integer bookmarkId = 1;
        Integer buyerId = 1;

        when(buyerService.getBuyerWithId(eq(buyerId))).thenReturn(Optional.of(mock(Buyer.class)));

        bookmarkServiceImpl.deleteBookmarkById(buyerId, bookmarkId);

        verify(bookmarkRepository, times(1)).deleteBookmarkWithBookmarkId(eq(bookmarkId));
    }

    @Test
    public void deleteBookmarkOfInexistentBuyerTest(){
        Integer bookmarkId = 1;
        Integer buyerId = 1;

        when(buyerService.getBuyerWithId(eq(buyerId))).thenReturn(Optional.empty());

        ElementNotFoundException exception = assertThrows(ElementNotFoundException.class, () -> bookmarkServiceImpl.deleteBookmarkById(buyerId, bookmarkId));

        assertEquals("Buyer with Id: " + buyerId + " not found", exception.getMessage());
        verify(bookmarkRepository, never()).deleteBookmarkWithBookmarkId(eq(bookmarkId));
    }
}
