package pl.jakub.walczak.driveme.repos.exam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakub.walczak.driveme.model.exam.TheoreticalExam;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public interface TheoreticalExamRepository extends JpaRepository<TheoreticalExam, Long> {
    Set<TheoreticalExam> findAllById(Set<Long> ids);
    List<TheoreticalExam> findAllByStudentIdOrderByPassedDescDateOfExamDesc(Long studentId);
}
