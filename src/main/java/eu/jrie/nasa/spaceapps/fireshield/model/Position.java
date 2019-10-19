package eu.jrie.nasa.spaceapps.fireshield.model;

public class Position {
    private double lat;
    private double lng;

    public Position() {
    }

    public Position(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Position) return lat == ((Position) obj).lat && lng == ((Position) obj).lng;
        else return false;
    }
}
