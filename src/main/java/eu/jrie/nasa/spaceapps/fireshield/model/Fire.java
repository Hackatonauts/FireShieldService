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

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public Instant getDate() {
        return date;
    }

    public Source getSource() {
        return source;
    }
}
