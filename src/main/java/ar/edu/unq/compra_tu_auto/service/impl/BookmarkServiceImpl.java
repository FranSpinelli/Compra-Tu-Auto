package ar.edu.unq.compra_tu_auto.service.impl;

import ar.edu.unq.compra_tu_auto.controller.DTO.bookmark.BookmarkRequestDTO;
import ar.edu.unq.compra_tu_auto.exception.ElementNotFoundException;
import ar.edu.unq.compra_tu_auto.mapper.BookmarkMapper;
import ar.edu.unq.compra_tu_auto.model.Bookmark;
import ar.edu.unq.compra_tu_auto.model.Car;
import ar.edu.unq.compra_tu_auto.repository.BookmarkRepository;
import ar.edu.unq.compra_tu_auto.service.BookmarkService;
import ar.edu.unq.compra_tu_auto.service.BuyerService;
import ar.edu.unq.compra_tu_auto.service.CarService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookmarkServiceImpl implements BookmarkService {

    private final BookmarkMapper bookmarkMapper;
    private final BookmarkRepository bookmarkRepository;
    private final BuyerService buyerService;
    private final CarService carService;

    public BookmarkServiceImpl(BookmarkMapper bookmarkMapper, BookmarkRepository bookmarkRepository, BuyerService buyerService, CarService carService) {
        this.bookmarkMapper = bookmarkMapper;
        this.bookmarkRepository = bookmarkRepository;
        this.buyerService = buyerService;
        this.carService = carService;
    }

    @Override
    public Bookmark createBookmark(Integer buyerId, BookmarkRequestDTO bookmarkRequestDTO) {
        verifyBuyerWithIdExists(buyerId);
        Car carToBeBookMarked = carService.getCarWithId(bookmarkRequestDTO.getCarDealershipId(), bookmarkRequestDTO.getCarId())
                .orElseThrow(() -> new ElementNotFoundException("Car", bookmarkRequestDTO.getCarId().toString()));

        Bookmark bookmarkToBeSaved = bookmarkMapper.mapFromRequestDTOToModel(bookmarkRequestDTO);
        bookmarkToBeSaved.setBuyerId(buyerId);
        bookmarkToBeSaved.setCar(carToBeBookMarked);

        return bookmarkRepository.saveBookmark(bookmarkToBeSaved);
    }

    @Override
    public Bookmark updateBookmark(Integer buyerId, Integer bookmarkId, BookmarkRequestDTO bookmarkRequestDTO) {
        verifyBuyerWithIdExists(buyerId);

        Bookmark foundBookmarkToEdit = bookmarkRepository.getBookmarkWithBookmarkId(bookmarkId)
                .orElseThrow(() -> new ElementNotFoundException("Bookmark", bookmarkId.toString()));

        foundBookmarkToEdit.setScore(bookmarkRequestDTO.getScore());
        foundBookmarkToEdit.setReview(bookmarkRequestDTO.getReview());

        return bookmarkRepository.saveBookmark(foundBookmarkToEdit);
    }

    @Override
    public Optional<Bookmark> getBookmarkWithBookmarkId(Integer buyerId, Integer bookmarkId) {
        verifyBuyerWithIdExists(buyerId);

        return bookmarkRepository.getBookmarkWithBookmarkId(bookmarkId);
    }

    @Override
    public void deleteBookmarkById(Integer buyerId, Integer bookmarkId) {
        verifyBuyerWithIdExists(buyerId);

        bookmarkRepository.deleteBookmarkWithBookmarkId(bookmarkId);
    }

    private void verifyBuyerWithIdExists(Integer buyerId) {
        buyerService.getBuyerWithId(buyerId).orElseThrow(() -> new ElementNotFoundException("Buyer", buyerId.toString()));
    }
}
