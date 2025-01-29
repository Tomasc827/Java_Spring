package lt.techin.Library.controllers;


import jakarta.validation.Valid;
import lt.techin.Library.dto.BookDTO;
import lt.techin.Library.model.Book;
import lt.techin.Library.repositories.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        if(books.isEmpty()) {
            return ResponseEntity.status(200).body(books);
        }
        return ResponseEntity.status(200).body(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable long id) {
        return bookRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    return ResponseEntity.status(404).build();
                });
    }

    @PostMapping
    public ResponseEntity<Book> saveBook(@RequestBody Book book) {
        if (book == null || book.getAuthor().isEmpty() || book.getTitle().isEmpty() || book.getMember() == null) {
            return ResponseEntity.status(400).build();
        }
        bookRepository.save(book);
        return ResponseEntity.status(201).body(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> replaceBook(@Valid @RequestBody BookDTO newBook, @PathVariable long id ) {
       return bookRepository.findById(id).map(book -> {
            book.setTitle(newBook.getTitle());
            book.setAuthor(newBook.getAuthor());
            book.setPublishingYear(newBook.getPublishingYear());
            bookRepository.save(book);
            return ResponseEntity.status(201).body(book);
        }).orElseGet(() -> ResponseEntity.status(404).build());
    }




}
