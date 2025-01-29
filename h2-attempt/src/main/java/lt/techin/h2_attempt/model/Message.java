package lt.techin.h2_attempt.model;

import jakarta.persistence.*;


import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Message {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(name = "Namito", nullable = false, columnDefinition = "VARCHAR(255)")
  private String name;

  @Column(name = "Contentisimo", nullable = false, columnDefinition = "VARCHAR(255)")
  private String content;

  @Column(name = "Time", columnDefinition = "TIMESTAMP")
  private Date date;

  @Column(name = "Steve", columnDefinition = "VARCHAR(99999)")
  private String steve;

  public Message() {
    this.date = new Date();
  }

  @PrePersist
  public void setSteve() {
    if (this.content != null && this.content.contains("steve")) {
      this.steve = "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⡀⢀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
              "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡴⠉⠁⠈⠀⠈⢵⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
              "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡅⣀⢠⣴⣾⠆⢮⣷⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
              "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⢿⡏⣩⣭⣀⠘⣇⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
              "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢳⣬⣿⢿⣿⠟⣇⣿⢇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
              "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⣿⣿⢋⡼⣿⠋⠈⣟⠂⠒⠒⠢⠤⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
              "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠤⢖⣿⢉⣿⣿⣥⣄⠀⡗⡀⠀⠀⡆⠀⢰⢸⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
              "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠔⠋⠀⠀⢺⣧⠘⣹⣿⡅⠀⢩⡇⠈⢙⣹⣧⣤⣾⠘⡻⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
              "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⡀⠀⠀⠀⠉⡿⡆⣿⣿⣿⡀⣸⠀⠀⠀⣿⣿⣿⣿⠁⠳⣷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
              "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡗⡇⠘⠂⠀⠀⢧⠸⣿⣿⣿⡷⠻⠀⠀⢸⠟⠛⠋⢻⡀⠀⢸⣷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
              "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣦⢀⠂⠀⠀⠀⢸⠤⠘⢿⣿⠁⠀⠀⠀⡞⠀⠀⠀⣹⣿⣦⣈⣛⣷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
              "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⣨⠀⠀⠀⠀⢸⡆⠄⢤⣿⠆⠀⠀⣸⠁⠀⠀⠀⣸⣿⣧⡙⠿⣿⣿⡦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
              "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⡞⣻⡄⠀⠀⠂⠌⡇⠀⠒⣷⣆⠀⢠⡏⠀⠀⠀⠀⢸⣿⣿⣿⣿⠋⠁⠀⠈⢦⡀⠀⠀⠀⠀⠀⠀⢀⡀⠀⠀⠀\n" +
              "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣼⣿⣿⣿⣷⠐⠀⠀⠀⢻⠀⠄⣧⣄⢰⣼⠀⠀⠀⠀⠀⠈⣿⡌⠻⣿⣭⣶⡶⠚⠛⠋⠒⠤⣄⣠⠤⠒⣡⡇⠀⠀⠀\n" +
              "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡠⠋⠀⠉⣿⣿⣿⡀⠀⠄⠰⡼⣟⣀⢽⣉⠀⣧⠀⠀⢀⠀⠀⠀⢻⣷⠀⠈⠛⣿⣷⣤⣴⠄⢀⢰⡟⠀⠀⠘⢛⠢⠤⣀⠀\n" +
              "⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣖⣦⡾⠡⠐⢒⣒⣻⣿⠟⡆⠀⠀⠀⠹⣿⡙⢿⣟⠁⡇⠀⠀⠀⠀⠀⠀⠈⣿⡆⠀⠀⠀⠙⠻⣷⣶⣸⡟⡀⠀⠐⠀⣬⠝⣒⣲⣽\n" +
              "⠀⠀⢀⣀⡀⣀⣀⣀⡠⠶⠉⡀⢄⢠⣞⣻⣿⠟⠁⠀⣧⠀⠀⠀⠀⢹⠇⠁⠛⠓⡇⠀⠀⠀⠀⢀⠀⠠⢼⣧⠀⠀⠀⠀⠀⠀⠙⢿⣧⡿⠒⠦⠤⠩⢽⠒⠋⠁\n" +
              "⠀⠀⠀⠳⣤⡄⢀⠈⢣⡠⣀⣴⣿⣿⡿⠛⠁⠀⠀⠀⣧⡀⠀⠀⠀⢸⣱⠀⢴⣒⣯⠴⠀⠀⠀⠀⣆⢀⣼⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
              "⠀⣀⠤⣒⡍⠀⡀⠀⢀⣿⣿⣿⡿⠋⠀⠀⢀⠀⣀⠀⡏⢹⠀⠀⠀⢾⡏⣧⡟⢿⣿⣂⡄⠀⠀⠀⠀⠀⣼⣿⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
              "⣮⢴⣪⠷⣋⠤⣨⡶⠟⠙⠛⠉⠀⠀⠀⠀⠈⠑⠾⠀⢹⣯⣢⢀⠀⣈⣿⣿⣇⡸⣿⣇⠂⠀⠀⠀⠠⠀⣽⣿⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
              "⠉⠛⣛⣯⣟⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣷⣠⣾⣿⣿⣿⣿⣿⣿⡦⢤⣶⣾⣯⣿⣿⣿⣿⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀";
    } else {
      this.steve = "no Steve";
    }
  }

  public String getFormattedDate() {
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    return formatter.format(date);
  }


  public String getId() {
    return id;
  }

  public String getSteve() {
    return steve;
  }


  public String getContent() {
    return content;
  }

  public String getName() {
    return name;
  }

  public void setContent(String content) {
    this.content = content != null ? content.trim() : null;
  }

  public void setName(String name) {
    this.name = name != null ? name.toLowerCase().trim() : null;
  }
}
