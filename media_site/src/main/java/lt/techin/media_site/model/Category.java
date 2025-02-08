package lt.techin.media_site.model;


import jakarta.persistence.*;
import lt.techin.media_site.model.media_enum.CategoryEnum;

import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private byte id;
    @Enumerated(value = EnumType.STRING)
    private CategoryEnum category;

    @ManyToMany
    private List<Media> medias;

    public Category() {

    }

    public byte getId() {
        return id;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public List<Media> getMedias() {
        return medias;
    }

    public void setMedias(List<Media> medias) {
        this.medias = medias;
    }
}
