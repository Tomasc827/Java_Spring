package lt.techin.Library.services;


import jakarta.validation.Valid;
import lt.techin.Library.models.Book;
import lt.techin.Library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

  private BookRepository bookRepository;

  @Autowired
  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }


  public List<Book> findAllBooks() {
    return bookRepository.findAll();
  }

  public Optional<Book> findBookById(long id) {
    return bookRepository.findById(id);
  }

  public void saveBook(@Valid Book book) {
    bookRepository.save(book);
  }

  public void deleteBookById(long id) {
    bookRepository.deleteById(id);
  }

  public void deleteAllBooks() {
    bookRepository.deleteAll();
  }


}
