package ar.edu.unq.compra_tu_auto.controller;

import ar.edu.unq.compra_tu_auto.controller.DTO.bookmark.BookmarkRequestDTO;
import ar.edu.unq.compra_tu_auto.controller.DTO.bookmark.BookmarkResponseDTO;
import ar.edu.unq.compra_tu_auto.mapper.BookmarkMapper;
import ar.edu.unq.compra_tu_auto.model.Bookmark;
import ar.edu.unq.compra_tu_auto.service.BookmarkService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<BookmarkResponseDTO> getBookmarkFromBuyerWithCarId(@PathVariable Integer bookmarkId, @PathVariable String buyerId) {
        Optional<Bookmark> foundBookmark = bookmarkService.getBookmarkWithBookmarkId(bookmarkId);

        return foundBookmark.map(bookmark -> ResponseEntity.ok(bookmarkMapper.mapFromModelToDTO(bookmark)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BookmarkResponseDTO> createBookmark(@RequestBody @Valid BookmarkRequestDTO bookmarkRequestDTO, @PathVariable String buyerId) {
        Bookmark bookmark = bookmarkService.createBookmark(bookmarkRequestDTO);
        return ResponseEntity.ok(bookmarkMapper.mapFromModelToDTO(bookmark));
    }

    @PutMapping("/{bookmarkId}")
    public ResponseEntity<BookmarkResponseDTO> updateBookmark(@PathVariable Integer bookmarkId, @RequestBody BookmarkRequestDTO bookmarkRequestDTO, @PathVariable String buyerId) {
        Bookmark updatedBookmark = bookmarkService.updateBookmark(bookmarkId, bookmarkRequestDTO);
        return ResponseEntity.ok(bookmarkMapper.mapFromModelToDTO(updatedBookmark));
    }

    @DeleteMapping("/{bookmarkId}")
    public ResponseEntity<Void> deleteBookmark(@PathVariable Integer bookmarkId, @PathVariable String buyerId) {
        bookmarkService.deleteBookmarkById(bookmarkId);
        return ResponseEntity.noContent().build();
    }
}
