package pl.jakub.walczak.driveme.repos.exam;

import org.springframework.data.repository.CrudRepository;
import pl.jakub.walczak.driveme.model.exam.Exam;

public interface ExamRepository extends CrudRepository<Exam, Long> {
}
