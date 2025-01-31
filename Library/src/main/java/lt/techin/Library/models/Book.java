package lt.techin.Library.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "books", schema = "Library")
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(nullable = false)
  private String title;
  @Column(length = 150, nullable = false)
  private String author;
  @Column(length = 4, nullable = false)
  private int publishingYear;
  @Column(length = 10, columnDefinition = "DECIMAL(10,2)")
  private BigDecimal price;
  @Column(name = "book_condition")
  @Enumerated(EnumType.STRING)
  private Condition condition;
  @Column(nullable = false)
  private boolean isRented;
  private String imageURL;

  @ManyToOne
  @JoinColumn(name = "member_id")
  @JsonBackReference
  private Member member;

  public Book() {

  }

  public Book(String imageURL, Condition condition, BigDecimal price, int publishingYear, String author, String title) {
    this.condition = condition;
    this.price = price;
    this.publishingYear = publishingYear;
    this.author = author;
    this.title = title;
    this.imageURL = imageURL;
    this.isRented = false;
    this.member = null;
  }

  public Member getMember() {
    return member;
  }

  public void setMember(Member member) {
    this.member = member;
  }

  public long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public int getPublishingYear() {
    return publishingYear;
  }

  public void setPublishingYear(int publishingYear) {
    this.publishingYear = publishingYear;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Condition getCondition() {
    return condition;
  }

  public void setCondition(Condition condition) {
    this.condition = condition;
  }

  public boolean isRented() {
    return isRented;
  }

  public void setRented(boolean rented) {
    isRented = rented;
  }

  public String getImageURL() {
    return imageURL;
  }

  public void setImageURL(String imageURL) {
    this.imageURL = imageURL;
  }
}
