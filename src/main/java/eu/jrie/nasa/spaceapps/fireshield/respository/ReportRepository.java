package eu.jrie.nasa.spaceapps.fireshield.respository;

import eu.jrie.nasa.spaceapps.fireshield.model.Report;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReportRepository extends MongoRepository<Report, String> {
    List<Report> findAllByFireId(final String fireId);
}
