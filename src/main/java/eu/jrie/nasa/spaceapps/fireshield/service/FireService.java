package eu.jrie.nasa.spaceapps.fireshield.service;

import eu.jrie.nasa.spaceapps.fireshield.model.Fire;
import eu.jrie.nasa.spaceapps.fireshield.model.Position;
import eu.jrie.nasa.spaceapps.fireshield.respository.FireRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
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

    public Fire addFire(final Fire fire) {
            fire.setPosition(
                    new Position(fire.getPosition().getLat(), fire.getPosition().getLng(), randomArea(fire.getPosition()))
            );
        return repository.insert(fire);
    }

    public Fire updateFire(final Fire fire) {
        return repository.save(fire);
    }

    private static final Random rand = new Random();
    private static List<List<Position>> randomArea(final Position center) {
        final List<List<Position>> area = new ArrayList<>();
        final int v = rand.nextInt(12) + 4;
        final int step = 360 / v;
        final List<Position> path = new ArrayList<>();
        final List<Position> outerPath = new ArrayList<>();
        for(int j = 0; j < v; j++) {
            final double angle = Math.toRadians(360 - (step * j));
            final double radius = (double)(rand.nextInt(100_000) + 1000) / 10_000_000;
            final double lat = (radius * Math.sin(angle));
            final double lng = (radius * Math.cos(angle));
               path.add(new Position(center.getLat() + lat,  center.getLng() + lng));
            outerPath.add(new Position(center.getLat() + lat*3,  center.getLng() + lng*3));
        }
        area.add(path);
        area.add(outerPath);
        return area;
    }
}
