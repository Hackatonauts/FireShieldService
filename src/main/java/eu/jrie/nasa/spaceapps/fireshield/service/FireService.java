package eu.jrie.nasa.spaceapps.fireshield.service;

import eu.jrie.nasa.spaceapps.fireshield.model.Fire;
import eu.jrie.nasa.spaceapps.fireshield.model.Position;
import eu.jrie.nasa.spaceapps.fireshield.respository.FireRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FireService {

    private final FireRepository repository;

    public FireService(FireRepository repository) {
        this.repository = repository;
    }

    public List<Fire> getFires(final Position position, final double radius) {
        return repository.findAll();
    }

    public Optional<Fire> getFire(final String id) {
        return repository.findById(id);
    }

    public Fire addFire(Fire fire) {
        return repository.save(fire);
    }

}
