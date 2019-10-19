package eu.jrie.nasa.spaceapps.fireshield.model;

import java.util.List;

public class Position {
    private double lat;
    private double lng;
    private List<List<Position>> area;

    public Position() {
    }

    public Position(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
        this.area = null;
    }

    public Position(double lat, double lng, List<List<Position>> area) {
        this.lat = lat;
        this.lng = lng;
        this.area = area;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public List<List<Position>> getArea() {
        return area;
    }

    public void setArea(List<List<Position>> area) {
        this.area = area;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Position) return lat == ((Position) obj).lat && lng == ((Position) obj).lng;
        else return false;
    }
}
