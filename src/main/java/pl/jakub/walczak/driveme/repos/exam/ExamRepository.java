package pl.jakub.walczak.driveme.repos.exam;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.jakub.walczak.driveme.model.exam.Exam;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ExamRepository extends CrudRepository<Exam, Long> {
}
