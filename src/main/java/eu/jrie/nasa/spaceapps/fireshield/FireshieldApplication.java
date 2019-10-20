package eu.jrie.nasa.spaceapps.fireshield;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FireshieldApplication {
    public static void main(String[] args) {
        SpringApplication.run(FireshieldApplication.class, args);
    }
}
