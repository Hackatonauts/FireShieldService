package eu.jrie.nasa.spaceapps.fireshield.rest.model.response;

import eu.jrie.nasa.spaceapps.fireshield.model.Position;
import eu.jrie.nasa.spaceapps.fireshield.rest.model.response.hateoas.FireModel;

import java.util.List;

public class FiresResponse {
    private final Position position;
    private final int radius;
    private final List<FireModel> fires;

    public FiresResponse(Position position, int radius, List<FireModel> fires) {
        this.position = position;
        this.radius = radius;
        this.fires = fires;
    }

    public Position getPosition() {
        return position;
    }

    public int getRadius() {
        return radius;
    }

    public List<FireModel> getFires() {
        return fires;
    }
}
