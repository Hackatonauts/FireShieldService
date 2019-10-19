package eu.jrie.nasa.spaceapps.fireshield.service;

import eu.jrie.nasa.spaceapps.fireshield.model.Report;
import eu.jrie.nasa.spaceapps.fireshield.respository.ReportRepository;
import org.springframework.stereotype.Service;

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
}
