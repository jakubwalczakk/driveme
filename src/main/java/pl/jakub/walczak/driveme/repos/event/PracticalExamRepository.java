package pl.jakub.walczak.driveme.repos.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakub.walczak.driveme.enums.CarBrand;
import pl.jakub.walczak.driveme.model.event.PracticalExam;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface PracticalExamRepository extends JpaRepository<PracticalExam, Long> {
    Optional<PracticalExam> findByStudentId(Long studentId);

    List<PracticalExam> findAllByInstructorId(Long instructorId);

    List<PracticalExam> findAllByCarBrand(CarBrand carBrand);

    List<PracticalExam> findAllByInstructorIdAndStartDateAfterOrderByStartDateDesc(Long instructorId, Instant startDate);

    List<PracticalExam> findAllByInstructorIdAndCarBrandAndStartDateAfterOrderByStartDateDesc(Long instructorId, CarBrand brand, Instant startDate);

    List<PracticalExam> findAllByCarBrandAndStartDateAfterAndFinishDateBefore(CarBrand carBrand, Instant startDate, Instant finishDate);

    List<PracticalExam> findAllByCarBrandAndStartDateBeforeAndFinishDateAfter(CarBrand carBrand, Instant startDate, Instant finishDate);
}
