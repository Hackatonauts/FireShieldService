package eu.jrie.nasa.spaceapps.fireshield.service;

import eu.jrie.nasa.spaceapps.fireshield.model.Fire;
import eu.jrie.nasa.spaceapps.fireshield.model.Position;
import eu.jrie.nasa.spaceapps.fireshield.respository.FireRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FireService {

    private final FireRepository repository;

    public FireService(FireRepository repository) {
        this.repository = repository;
    }

    public List<Fire> getFires(final Position position, final int radius) {
        return repository.findAll()
                .stream()
                .filter(f -> GeoService.calculateDistance(position, f.getPosition()) <= radius)
                .collect(Collectors.toList());
    }

    public Optional<Fire> getFire(final String id) {
        return repository.findById(id);
    }

    public Fire addFire(Fire fire) {
        return repository.insert(fire);
    }

}
