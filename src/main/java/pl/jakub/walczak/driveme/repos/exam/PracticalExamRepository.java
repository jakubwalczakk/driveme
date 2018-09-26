package pl.jakub.walczak.driveme.repos.exam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakub.walczak.driveme.model.exam.PracticalExam;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PracticalExamRepository extends JpaRepository<PracticalExam, Long> {
}
