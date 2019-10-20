package eu.jrie.nasa.spaceapps.fireshield.rest;

import eu.jrie.nasa.spaceapps.fireshield.model.Fire;
import eu.jrie.nasa.spaceapps.fireshield.model.Position;
import eu.jrie.nasa.spaceapps.fireshield.rest.model.response.FiresResponse;
import eu.jrie.nasa.spaceapps.fireshield.rest.model.response.hateoas.FireModel;
import eu.jrie.nasa.spaceapps.fireshield.service.FireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
public class FireController {

    private final FireService fireService;

    @Autowired
    public FireController(FireService fireService) {
        this.fireService = fireService;
    }

    @GetMapping("fires")
    public ResponseEntity<FiresResponse> getFires(
            @RequestParam final double lat, @RequestParam final double lng, @RequestParam final int radius,
            @RequestParam(required = false, defaultValue = "all") final String status
    ) {
        final Position position = new Position(lat, lng);
        final List<Fire> fires = status.equals("all") ?
                fireService.getFires(position, radius) : fireService.getFires(position, radius, status.toLowerCase());
        if(fires.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        else return ResponseEntity.ok(new FiresResponse(position, radius, fires.stream()
                .map(f -> {
                    FireModel fireModel = new FireModel(f);
                    fireModel.add(WebMvcLinkBuilder.linkTo(methodOn(FireController.class).getFire(f.getId())).withSelfRel());
                    fireModel.add(WebMvcLinkBuilder.linkTo(methodOn(ReportController.class).getReports(f.getId())).withRel("reports"));
                    return fireModel;
                })
                .collect(Collectors.toList())));
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

    @PutMapping("fire")
    public ResponseEntity<Fire> putFire(@RequestBody final Fire fire) {
        return ResponseEntity.ok(fireService.updateFire(fire));
    }

    @PutMapping("fire/{id}/close")
    public ResponseEntity<Void> putFireStatus(@PathVariable final String id) {
        fireService.closeFire(id);
        return ResponseEntity.ok().build();
    }

}
