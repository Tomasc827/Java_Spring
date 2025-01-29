package lt.techin.Library.service;

import lt.techin.Library.dto.BookDTO;
import lt.techin.Library.model.Book;
import lt.techin.Library.model.Member;
import lt.techin.Library.repositories.BookRepository;
import lt.techin.Library.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MemberRepository memberRepository;



    public Book createBook(BookDTO dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setPublishingYear(dto.getPublishingYear());
        Member member = memberRepository.findById(dto.getMemberID()).orElseThrow(() -> new RuntimeException("Member not found"));
        book.setMember(member);

        return bookRepository.save(book);
    }
}
