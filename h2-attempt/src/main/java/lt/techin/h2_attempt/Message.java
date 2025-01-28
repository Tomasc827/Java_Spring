package lt.techin.h2_attempt;

import jakarta.persistence.*;


import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Namito", nullable = false, columnDefinition = "VARCHAR(255)")
    private String name;

    @Column(name = "Contentisimo" ,nullable = false,columnDefinition = "VARCHAR(255)")
    private String content;

    @Column(name = "Time",columnDefinition = "TIMESTAMP")
    private Date date;

    public Message(){
    this.date = new Date();
    }

    public String getFormattedDate() {
      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
      return formatter.format(date);
    }

    public long getId() {
        return id;
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
