package pl.jakub.walczak.driveme.repos.exam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakub.walczak.driveme.model.exam.Exam;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ExamRepository extends JpaRepository<Exam, Long> {
}
