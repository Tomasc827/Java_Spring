package lt.techin.Library.model;

import jakarta.persistence.*;
import lt.techin.Library.exceptions.BookLimitException;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String yearJoined;

    @OneToMany(mappedBy = "member")
    private List<Book> books = new ArrayList<>();

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getYearJoined() {
        return yearJoined;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYearJoined(String yearJoined) {
        this.yearJoined = yearJoined;
    }

    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }
    public void addBook(Book book) {
        if(books.size() <= 3) {
            books.add(book);
        } else {
            throw new BookLimitException("A member can only have up to three books at any given time");
        }

    }

    public void removeBook(Book book) {
        books.remove(book);
        book.setMember(null);
    }

}
