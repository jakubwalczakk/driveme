package pl.jakub.walczak.driveme.repos.event.exam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakub.walczak.driveme.enums.CarBrand;
import pl.jakub.walczak.driveme.model.event.exam.PracticalExam;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface PracticalExamRepository extends JpaRepository<PracticalExam, Long> {
    Optional<PracticalExam> findByStudentId(Long studentId);

    List<PracticalExam> findAllByInstructorIdOrderByStartDateDesc(Long instructorId);

    List<PracticalExam> findAllByInstructorId(Long instructorId);

    List<PracticalExam> findAllByCarBrand(CarBrand carBrand);

    List<PracticalExam> findAllByInstructorIdAndCarBrand(Long instructorId, CarBrand brand);

    List<PracticalExam> findAllByCarBrandAndStartDateAfterAndFinishDateBefore(CarBrand carBrand, Instant startDate, Instant finishDate);

    List<PracticalExam> findAllByCarBrandAndStartDateBeforeAndFinishDateAfter(CarBrand carBrand, Instant startDate, Instant finishDate);
}
