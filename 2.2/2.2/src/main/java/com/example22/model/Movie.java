package com.example22.model;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.stream.Collectors;

@Entity
public class Movie {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, columnDefinition = "VARCHAR(255)")
  private String title;

  @Column(nullable = false, columnDefinition = "VARCHAR(255)")
  private String director;

  public Movie() {

  }

  public String getTitle() {
    return title;
  }

  public String getDirector() {
    return director;
  }

  public Long getId() {
    return id;
  }

  public void setTitle(String title) {
    this.title = Arrays.stream(title.split(" ")).map(word -> {
      String fL = word.substring(0, 1).toUpperCase();
      String rest = word.substring(1).toLowerCase();
      return fL + rest;
    }).collect(Collectors.joining(" "));
  }

  public void setDirector(String director) {
    this.director = Arrays.stream(director.split(" ")).map(word -> {
      String fL = word.substring(0, 1).toUpperCase();
      String rest = word.substring(1).toLowerCase();
      return fL + rest;
    }).collect(Collectors.joining(" "));
  }
}
