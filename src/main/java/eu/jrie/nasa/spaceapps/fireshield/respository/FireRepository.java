package eu.jrie.nasa.spaceapps.fireshield.respository;

import eu.jrie.nasa.spaceapps.fireshield.model.Fire;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FireRepository extends MongoRepository<Fire, String> {
}
