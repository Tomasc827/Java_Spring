package lt.techin.Library.controllers;


import jakarta.validation.Valid;
import lt.techin.Library.dtos.BookDTO;
import lt.techin.Library.exceptions.BookNotFoundException;
import lt.techin.Library.exceptions.BookNotRentedException;
import lt.techin.Library.exceptions.BookRentedException;
import lt.techin.Library.models.Book;
import lt.techin.Library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
  private BookService bookService;

  @Autowired
  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping
  public ResponseEntity<List<Book>> getAllBooks() {
    List<Book> books = bookService.findAllBooks();
    return ResponseEntity.ok(books);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Book> getBookById(@PathVariable long id) {

    return bookService.findBookById(id)
            .map(ResponseEntity::ok)
            .orElseThrow(() -> new BookNotFoundException(id));
  }

  @PostMapping
  public ResponseEntity<Book> addBook(@Valid @RequestBody BookDTO bookDto) {
    Book newBook = new Book();
    return setBookInfo(bookDto, newBook);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Book> replaceBook(@Valid @RequestBody BookDTO bookDTO, @PathVariable long id) {
    return bookService.findBookById(id).map(book -> {
      return setBookInfo(bookDTO, book);
    }).orElseThrow(() -> new BookNotFoundException(id));
  }

  private ResponseEntity<Book> setBookInfo(@RequestBody @Valid BookDTO bookDTO, Book book) {
    book.setAuthor(bookDTO.author());
    book.setTitle(bookDTO.title());
    book.setCondition(bookDTO.condition());
    book.setRented(false);
    book.setPrice(bookDTO.price());
    book.setPublishingYear(bookDTO.publishingYear());
    book.setImageURL(bookDTO.imageURL());
    bookService.saveBook(book);
    URI location = URI.create("/books/" + book.getId());
    return ResponseEntity.created(location).body(book);
  }

  @PatchMapping("/{id}/rent")
  public ResponseEntity<Book> rentBook(@PathVariable long id) {
    return bookService.findBookById(id).map(book -> {
      if (book.isRented()) {
        throw new BookRentedException(id);
      }

      book.setRented(true);

      bookService.saveBook(book);
      return ResponseEntity.ok(book);

    }).orElseThrow(() -> new BookNotFoundException(id));
  }

  @PatchMapping("/{id}/return")
  public ResponseEntity<Book> returnBook(@PathVariable long id) {
    return bookService.findBookById(id).map(book -> {
      if (!book.isRented()) {
        throw new BookNotRentedException(id);
      }

      book.setRented(false);

      bookService.saveBook(book);

      return ResponseEntity.ok(book);
    }).orElseThrow(() -> new BookNotFoundException(id));
  }

  @DeleteMapping
  public void deleteAllBooks() {
    bookService.deleteAllBooks();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteBook(@PathVariable long id) {
    return bookService.findBookById(id)
            .map(book -> {
              bookService.deleteBookById(id);
              return ResponseEntity.noContent().build();
            }).orElseThrow(() -> new BookNotFoundException(id));
  }


}
