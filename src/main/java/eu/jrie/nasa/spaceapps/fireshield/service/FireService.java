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
    private final NotificationService notificationService;

    public FireService(FireRepository repository, NotificationService notificationService) {
        this.repository = repository;
        this.notificationService = notificationService;
    }

    public List<Fire> getFires(final Position position, final int radius) {
        final List<Fire> fires = repository.findAll();
        return GeoService.filterByDistance(fires.stream(), Fire::getPosition, position, radius)
                .collect(Collectors.toList());
    }

    public List<Fire> getFires(final Position position, final int radius, final String status) {
        final List<Fire> fires = repository.findAllByStatus(status);
        return GeoService.filterByDistance(fires.stream(), Fire::getPosition, position, radius)
                .collect(Collectors.toList());
    }

    public Optional<Fire> getFire(final String id) {
        return repository.findById(id);
    }

    public Fire addFire(final Fire fire) {
        if(fire.getPosition().getArea() == null) {
            fire.setPosition(
                    new Position(fire.getPosition().getLat(), fire.getPosition().getLng(), randomArea(fire.getPosition()))
            );
        }
        else if(fire.getPosition().getArea().size() == 1) {
            fire.getPosition().getArea().add(enlargeArea(fire.getPosition().getArea().get(0)));
        }
        final Fire inserted = repository.insert(fire);
        notificationService.sendFireAlerts(inserted.getPosition(), inserted.getId());
        return inserted;
    }

    public Fire updateFire(final Fire fire) {
        return repository.save(fire);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public void closeFire(final String id) {
        final Fire fire = getFire(id).get();
        fire.setStatus("closed");
        updateFire(fire);
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
            path.add(new Position(center.getLat() + lat, center.getLng() + lng));
            outerPath.add(new Position(center.getLat() + lat*3, center.getLng() + lng*3));
        }
        area.add(path);
        area.add(outerPath);
        return area;
    }
    private static List<Position> enlargeArea(final List<Position> path) {
        final int step = 360 / path.size();
        final List<Position> outerPath = new ArrayList<>();
        for(int j = 0; j < path.size(); j++) {
            final double angle = Math.toRadians(360 - (step * j));
            outerPath.add(
                    new Position(
                            path.get(j).getLat() + .008*Math.signum(Math.sin(angle)),
                            path.get(j).getLng() + .008*Math.signum(Math.cos(angle))
                    )
            );
        }
        return outerPath;
    }
}
