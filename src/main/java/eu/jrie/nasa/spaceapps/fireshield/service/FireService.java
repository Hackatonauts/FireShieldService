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

    public Fire addFire(Fire fire) {
            fire.setPosition(
                    new Position(fire.getPosition().getLat(), fire.getPosition().getLng(), randomArea(fire.getPosition()))
            );
        return repository.insert(fire);
    }

    private static final Random rand = new Random();
    private static List<List<Position>> randomArea(final Position center) {
        final List<List<Position>> area = new ArrayList<>();
        final int levels = 2;
        for (int i = 0; i < levels; i++) {
            final int v = rand.nextInt(12) + 4;
            final int step = 360 / v;
            final List<Position> path = new ArrayList<>();
            for(int j = 0; j < v; j++) {
                final double angle = Math.toRadians(360 - (step * j));
                final double radius = (double)(rand.nextInt(10_000) + 100) / 10_000_000;
                final double lat = center.getLat() + (radius * Math.sin(angle));
                final double lng = center.getLng() + (radius * Math.cos(angle));
                path.add(new Position(lat,  lng));
            }
            area.add(path);
        }
        return area;
    }
}
