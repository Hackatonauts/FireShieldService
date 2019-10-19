package eu.jrie.nasa.spaceapps.fireshield.model;

public class Source {
    private final String type;
    private final String id;

    public Source(String type, String id) {
        this.type = type;
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }
}
