package eu.jrie.nasa.spaceapps.fireshield.rest;

import eu.jrie.nasa.spaceapps.fireshield.model.Fire;
import eu.jrie.nasa.spaceapps.fireshield.model.Position;
import eu.jrie.nasa.spaceapps.fireshield.service.FireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class FireController {

    private final FireService fireService;

    @Autowired
    public FireController(FireService fireService) {
        this.fireService = fireService;
    }

    @GetMapping("fires")
    public List<Fire> getFires(final double lat, final double lng, final double radius) {
        return fireService.getFires(new Position(lat, lng), radius);
    }

    @GetMapping("fire/{id}")
    public ResponseEntity<Fire> getFire(@PathVariable final String id) {
        return ResponseEntity.of(fireService.getFire(id));
    }

    @PostMapping("fire")
    public ResponseEntity<Fire> postFire(@RequestBody final Fire fire) {
        final Fire created = fireService.addFire(fire);
        return ResponseEntity.created(URI.create("fire/" + created.getId()))
                .body(created);
    }

}
