package eu.jrie.nasa.spaceapps.fireshield.respository;

import eu.jrie.nasa.spaceapps.fireshield.model.Weather;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WeatherRepository extends MongoRepository<Weather, String> {
    Weather findFirstByFireId(final String fireId);
}
