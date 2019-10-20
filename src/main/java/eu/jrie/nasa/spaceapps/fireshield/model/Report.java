package eu.jrie.nasa.spaceapps.fireshield.model;

import org.springframework.data.annotation.Id;

import java.time.Instant;
import java.util.List;

public class Report {
    @Id
    private String id;
    private String fireId;
    private String description;
    private List<String> images;
    private String user;
    private Position position;
    private Instant date = Instant.now();

    public Report() {
    }

    public Report(String id, String fireId, String description, List<String> images, String user, Position position, Instant date) {
        this.id = id;
        this.fireId = fireId;
        this.description = description;
        this.images = images;
        this.user = user;
        this.position = position;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFireId() {
        return fireId;
    }

    public void setFireId(String fireId) {
        this.fireId = fireId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }
}
