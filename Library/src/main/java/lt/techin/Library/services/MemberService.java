package lt.techin.Library.services;

import lt.techin.Library.exceptions.*;
import lt.techin.Library.models.Book;
import lt.techin.Library.models.Member;
import lt.techin.Library.repositories.BookRepository;
import lt.techin.Library.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class MemberService {

  private MemberRepository memberRepository;
  private BookRepository bookRepository;

  @Autowired
  public MemberService(MemberRepository memberRepository, BookRepository bookRepository) {
    this.memberRepository = memberRepository;
    this.bookRepository = bookRepository;
  }


  public List<Member> findAllMember() {
    return memberRepository.findAll();
  }

  public Optional<Member> findMemberById(long id) {
    return memberRepository.findById(id);
  }

  public void saveMember(Member member) {
    memberRepository.save(member);
  }

  public String returnBookFromMember(long memberId, long bookId) {
    Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new MemberNotFoundException(memberId));

    Book book = bookRepository.findById(bookId)
            .orElseThrow(() -> new BookNotFoundException(bookId));
    if (!book.isRented()) {
      throw new BookNotRentedException(bookId);
    }
    if (book.getMember().getId() != memberId) {
      throw new InvalidReturnException("Book with id " + bookId + " '" + book.getTitle() + "' was not rented by the member with id " + memberId);
    }

    book.setRented(false);

    member.getRentedBooks().remove(book);
    book.setMember(null);
    memberRepository.save(member);
    bookRepository.save(book);

    return "Book returned successfully";

  }

  public String rentBookToMember(long memberId, long bookId) {
    Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new MemberNotFoundException(memberId));

    Book book = bookRepository.findById(bookId)
            .orElseThrow(() -> new BookNotFoundException(bookId));

    if (member.getRentedBooks().size() >= 3) {
      throw new BookLimitException(memberId);
    }
    if (book.isRented()) {
      throw new BookRentedException(bookId);
    }


    member.getRentedBooks().add(book);
    book.setMember(member);
    book.setRented(true);

    memberRepository.save(member);
    bookRepository.save(book);

    return "Book rented Successfully";
  }

  public boolean existsByEmail(String email) {
    return memberRepository.existsByEmail(email);
  }

  public boolean existsByPassword(String password) {
    return memberRepository.existsByPassword(password);
  }
  public boolean validateCredentials(String email, String password) {
      return memberRepository.findByEmail(email)
              .map(member -> member.getPassword().equals(password))
              .orElse(false);
  }

  public Optional<Member> findByEmail(String email) {
      return memberRepository.findByEmail(email);
  }


}
