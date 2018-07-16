package pl.jakub.walczak.driveme.repos;

import org.springframework.data.repository.CrudRepository;
import pl.jakub.walczak.driveme.model.Exam;

public interface ExamRepository extends CrudRepository<Exam, Long> {
}
