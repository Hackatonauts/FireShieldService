package eu.jrie.nasa.spaceapps.fireshield.service;

import eu.jrie.nasa.spaceapps.fireshield.model.Position;

import java.util.function.Function;
import java.util.stream.Stream;

class GeoService {
    private static int calculateDistance(final Position a, final Position b) {
        if (a.equals(b)) return 0;
        else {
            final double theta = a.getLng() - b.getLng();
            double dist = Math.sin(Math.toRadians(a.getLat())) * Math.sin(Math.toRadians(b.getLat())) + Math.cos(Math.toRadians(a.getLat())) * Math.cos(Math.toRadians(b.getLat())) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            return (int) (dist * 1.609344 * 1000);
        }
    }

    static <A> Stream<A> filterByDistance(
            final Stream<A> stream, Function<A, Position> map, final Position position, final int radius
    ) {
        return stream.filter(a -> calculateDistance(position, map.apply(a)) <= radius);
    }
}
