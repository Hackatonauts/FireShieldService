package eu.jrie.nasa.spaceapps.fireshield.service;

import eu.jrie.nasa.spaceapps.fireshield.config.FileSystemConfig;
import eu.jrie.nasa.spaceapps.fireshield.model.Report;
import eu.jrie.nasa.spaceapps.fireshield.respository.ReportRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    private final ReportRepository repository;

    public ReportService(ReportRepository repository) {
        this.repository = repository;
    }

    public Optional<Report> getReport(final String id) {
        return repository.findById(id);
    }

    public List<Report> getReportsForFire(final String fireId) {
        return repository.findAllByFireId(fireId);
    }

    public Report addReport(final Report report) {
        return repository.insert(report);
    }

    private static int nextImgId = 1;
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public Report addImage(final String reportId, final MultipartFile image) throws IOException {
        final String name = "img_" + reportId    + "_" + nextImgId++ + ".jpg";
        final File imgFile = new File(FileSystemConfig.IMG_PATH + "/" + name);
        image.transferTo(imgFile);
        final Report report = getReport(reportId).get();
        report.getImages().add(FileSystemConfig.IMG_URL + "/img/" + name);
        return repository.save(report);
    }
}
