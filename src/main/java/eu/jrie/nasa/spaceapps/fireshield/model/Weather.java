package eu.jrie.nasa.spaceapps.fireshield.model;

import org.springframework.data.annotation.Id;

import java.time.Instant;

public class Weather {

    @Id
    private String id;
    private String name;
    private double temperature;
    private double windSpeed;
    private double windDegrees;
    private String type;
    private Instant date;
    private String fireId;

    public Weather() {
    }

    public Weather(String id, String name, double temperature, double windSpeed, double windDegrees, String type, Instant date, String fireId) {
        this.id = id;
        this.name = name;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.windDegrees = windDegrees;
        this.type = type;
        this.date = date;
        this.fireId = fireId;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getWindDegrees() {
        return windDegrees;
    }

    public void setWindDegrees(double windDegrees) {
        this.windDegrees = windDegrees;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getFireId() {
        return fireId;
    }

    public void setFireId(String fireId) {
        this.fireId = fireId;
    }
}
