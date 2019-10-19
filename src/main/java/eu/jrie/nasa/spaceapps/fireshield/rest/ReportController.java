package eu.jrie.nasa.spaceapps.fireshield.rest;

import eu.jrie.nasa.spaceapps.fireshield.model.Report;
import eu.jrie.nasa.spaceapps.fireshield.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin
public class ReportController {

    private final ReportService service;

    public ReportController(ReportService service) {
        this.service = service;
    }

    @GetMapping("reports")
    public List<Report> getReports(@RequestParam final String fireId) {
        return service.getReportsForFire(fireId);
    }

    @GetMapping("report")
    public ResponseEntity<Report> getReport(final String id) {
        return ResponseEntity.of(service.getReport(id));
    }

    @PostMapping("report")
    public ResponseEntity<Report> postReport(@RequestBody final Report report) {
        final Report created = service.addReport(report);
        return ResponseEntity.created(URI.create("report/" + created.getId()))
                .body(created);
    }
}
