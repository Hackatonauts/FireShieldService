package eu.jrie.nasa.spaceapps.fireshield.respository;

import eu.jrie.nasa.spaceapps.fireshield.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
