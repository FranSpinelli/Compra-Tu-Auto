package ar.edu.unq.compra_tu_auto.controller;

import ar.edu.unq.compra_tu_auto.controller.DTO.bookmark.BookmarkRequestDTO;
import ar.edu.unq.compra_tu_auto.controller.DTO.bookmark.BookmarkResponseDTO;
import ar.edu.unq.compra_tu_auto.mapper.BookmarkMapper;
import ar.edu.unq.compra_tu_auto.model.Bookmark;
import ar.edu.unq.compra_tu_auto.service.BookmarkService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@RestController
@RequestMapping("/buyers/{buyerId}/bookmarks")
public class BookmarkController {

    private final BookmarkMapper bookmarkMapper;
    private final BookmarkService bookmarkService;

    public BookmarkController(BookmarkMapper bookmarkMapper, BookmarkService bookmarkService) {
        this.bookmarkMapper = bookmarkMapper;
        this.bookmarkService = bookmarkService;
    }

    @GetMapping("/{bookmarkId}")
    public ResponseEntity<BookmarkResponseDTO> getBookmarkFromBuyerWithCarId(@PathVariable Integer bookmarkId, @PathVariable Integer buyerId) {
        Optional<Bookmark> foundBookmark = bookmarkService.getBookmarkWithBookmarkId(buyerId, bookmarkId);

        return foundBookmark.map(bookmark -> ResponseEntity.ok(bookmarkMapper.mapFromModelToResponseDTO(bookmark)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BookmarkResponseDTO> createBookmark(@RequestBody @Valid BookmarkRequestDTO bookmarkRequestDTO, @PathVariable Integer buyerId) {
        Bookmark bookmark = bookmarkService.createBookmark(buyerId, bookmarkRequestDTO);
        return ResponseEntity.ok(bookmarkMapper.mapFromModelToResponseDTO(bookmark));
    }

    @PutMapping("/{bookmarkId}")
    public ResponseEntity<BookmarkResponseDTO> updateBookmark(@PathVariable Integer bookmarkId, @RequestBody BookmarkRequestDTO bookmarkRequestDTO, @PathVariable Integer buyerId) {
        Bookmark updatedBookmark = bookmarkService.updateBookmark(buyerId, bookmarkId, bookmarkRequestDTO);
        return ResponseEntity.ok(bookmarkMapper.mapFromModelToResponseDTO(updatedBookmark));
    }

    @DeleteMapping("/{bookmarkId}")
    public ResponseEntity<Void> deleteBookmark(@PathVariable Integer bookmarkId, @PathVariable Integer buyerId) {
        bookmarkService.deleteBookmarkById(buyerId, bookmarkId);
        return ResponseEntity.noContent().build();
    }
}
