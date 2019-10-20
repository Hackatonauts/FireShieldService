package eu.jrie.nasa.spaceapps.fireshield.respository;

import eu.jrie.nasa.spaceapps.fireshield.model.Fire;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FireRepository extends MongoRepository<Fire, String> {
    List<Fire> findAllByStatus(final String status);
}
