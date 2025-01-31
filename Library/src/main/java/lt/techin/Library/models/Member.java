package lt.techin.Library.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Members")
public class Member {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(length = 100, nullable = false)
  private String name;

  @Column(length = 100, nullable = false)
  private String email;
  @Column(nullable = false)
  private String password;
  @OneToMany(mappedBy = "member")
  @JsonIgnore
  private List<Book> rentedBooks;

  public Member() {

  }

  public Member(String name, String email, String password) {
    this.name = name;
    this.email = email;
    this.password = password;
  }

  public long getId() {
    return id;
  }

  public List<Book> getRentedBooks() {
    return rentedBooks;
  }

  public void setRentedBooks(List<Book> rentedBooks) {
    this.rentedBooks = rentedBooks;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
