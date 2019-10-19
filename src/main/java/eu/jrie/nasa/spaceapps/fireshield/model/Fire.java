package eu.jrie.nasa.spaceapps.fireshield.model;

import org.springframework.data.annotation.Id;

import java.time.Instant;


public class Fire {
    @Id
    private String id;
    private String name;
    private Position position;
    private Instant date;
    private Source source;

    public Fire() { }

    public Fire(String id, String name, Position position, Instant date, Source source) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.date = date;
        this.source = source;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }
}
