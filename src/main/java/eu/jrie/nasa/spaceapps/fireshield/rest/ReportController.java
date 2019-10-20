package eu.jrie.nasa.spaceapps.fireshield.rest;

import eu.jrie.nasa.spaceapps.fireshield.model.Report;
import eu.jrie.nasa.spaceapps.fireshield.service.ReportService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

//    @PostMapping("report/img")
//    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
//        storageService.store(file);
//        redirectAttributes.addFlashAttribute("message",
//                "You successfully uploaded " + file.getOriginalFilename() + "!");
//
//        return "redirect:/";
//    }

    @PostMapping(value = "report/img", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Report> addImage(
            @RequestParam String reportId,
            @RequestParam MultipartFile image
    ) throws IOException {
        final Report report = service.addImage(reportId, image);
        return ResponseEntity.created(URI.create(report.getImages().get(report.getImages().size()-1)))
                .body(report);
    }
}
