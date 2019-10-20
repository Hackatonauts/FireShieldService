package eu.jrie.nasa.spaceapps.fireshield.model;

import org.springframework.data.annotation.Id;

public class User {
    @Id
    private String id;
    private String name;
    private String fbId;
    private Position position;
    private int radius;

    public User() {
    }

    public User(String name, String fbId, Position position, int radius) {
        this.name = name;
        this.fbId = fbId;
        this.position = position;
        this.radius = radius;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
