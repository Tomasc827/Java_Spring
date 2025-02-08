package lt.techin.media_site.model;

import jakarta.persistence.*;
import lt.techin.media_site.model.media_enum.AgeRating;
import lt.techin.media_site.model.media_enum.Type;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "medias")
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private LocalDate releaseDate;
    private int episodeCount;
    private int episodeTotal;
    private String videoURL;
    private String imageURL;
    @Enumerated(value = EnumType.STRING)
    private Type type;
    private String description;
    @Enumerated(value = EnumType.STRING)
    private AgeRating ageRating;

    @ManyToMany
    @JoinTable(name = "media_categories",
    joinColumns = @JoinColumn(name = "media_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;
    public Media() {

    }

    public long getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getEpisodeCount() {
        return episodeCount;
    }

    public void setEpisodeCount(int episodeCount) {
        this.episodeCount = episodeCount;
    }

    public int getEpisodeTotal() {
        return episodeTotal;
    }

    public void setEpisodeTotal(int episodeTotal) {
        this.episodeTotal = episodeTotal;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AgeRating getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(AgeRating ageRating) {
        this.ageRating = ageRating;
    }
}
