package eu.jrie.nasa.spaceapps.fireshield.service;

import eu.jrie.nasa.spaceapps.fireshield.model.Position;
import eu.jrie.nasa.spaceapps.fireshield.model.Weather;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Service
public class WeatherService {

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String WEATHER_SERVICE_URL = "http://api.openweathermap.org/data/2.5/weather?lat=%1$,.2f&lon=%2$,.2f&unit=metric&APPID=";
    private static final String API_KEY = "5b8eeaa8073590250b89e2805d9677f4";

    public WeatherService() {

    }

    public Weather getWeather(Position position) {
        NewWeather newWeather = restTemplate.getForObject(String.format(WEATHER_SERVICE_URL, position.getLat(), position.getLng()) + API_KEY, NewWeather.class);
        Weather weather = new Weather(newWeather.getId(),
                newWeather.getName(),
                newWeather.getMainDetails().getTemp(),
                newWeather.getWind().getSpeed(),
                newWeather.getWind().getDeg(),
                newWeather.getWeatherDetails().getMain(),
                newWeather.getDate());
        return weather;
    }

    private static class NewWeather {
        private WeatherDetails weather;
        private MainDetails main;
        private Wind wind;
        private int id;
        private String name;
        private Instant date;

        public NewWeather() {
        }

        public NewWeather(WeatherDetails weather, MainDetails main, int id, String name, Wind wind) {
            this.weather = weather;
            this.main = main;
            this.id = id;
            this.name = name;
            this.wind = wind;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public MainDetails getMainDetails() {
            return main;
        }

        public void setMainDetails(MainDetails main) {
            this.main = main;
        }

        public WeatherDetails getWeatherDetails() {
            return weather;
        }

        public void setWeatherDetails(WeatherDetails weather) {
            this.weather = weather;
        }

        public Wind getWind() {
            return wind;
        }

        public void setWind(Wind wind) {
            this.wind = wind;
        }

        public Instant getDate() {
            return date;
        }

        public void setDate(Instant date) {
            this.date = date;
        }
    }

    private static class WeatherDetails {

        private String main;

        public WeatherDetails() {}

        public WeatherDetails(String main) {
            this.main = main;
        }

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }
    }

    private static class MainDetails {
        private double temp;

        public MainDetails() {}

        public MainDetails(double temp) {
            this.temp = temp;
        }

        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }
    }

    private static class Wind {
        private double speed;
        private double deg;

        public Wind() {}

        public Wind(double speed, double deg) {
            this.speed = speed;
            this.deg = deg;
        }

        public double getSpeed() {
            return speed;
        }

        public void setSpeed(double speed) {
            this.speed = speed;
        }

        public double getDeg() {
            return deg;
        }

        public void setDeg(double deg) {
            this.deg = deg;
        }
    }
}
