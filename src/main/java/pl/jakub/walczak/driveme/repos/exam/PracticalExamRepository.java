package pl.jakub.walczak.driveme.repos.exam;

import org.springframework.data.repository.CrudRepository;
import pl.jakub.walczak.driveme.model.exam.PracticalExam;

public interface PracticalExamRepository extends CrudRepository<PracticalExam,Long> {
}
