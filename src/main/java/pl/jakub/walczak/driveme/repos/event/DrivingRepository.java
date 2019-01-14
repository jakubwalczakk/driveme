package pl.jakub.walczak.driveme.repos.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakub.walczak.driveme.enums.CarBrand;
import pl.jakub.walczak.driveme.model.event.Driving;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public interface DrivingRepository extends JpaRepository<Driving, Long> {
    List<Driving> findAllByInstructorId(Long instructorId);

    List<Driving> findAllByCarBrand(CarBrand carBrand);

    List<Driving> findAllByStudentIdOrderByStartDateDesc(Long studentId);

    List<Driving> findAllByInstructorIdAndStartDateAfterOrderByStartDateDesc(Long instructorId, Instant startDate);

    List<Driving> findAllByInstructorIdAndStartDateAfterAndFinishDateBeforeOrderByStartDateDesc(Long instructorId, Instant startDate, Instant finishDate);

    List<Driving> findAllByInstructorIdAndCarBrandAndStartDateAfterOrderByStartDateDesc(Long instructorId, CarBrand carBrand, Instant startDate);

    List<Driving> findAllByCarBrandAndStartDateAfterAndFinishDateBefore(CarBrand carBrand, Instant startDate, Instant finishDate);

    List<Driving> findAllByCarBrandAndStartDateBeforeAndFinishDateAfter(CarBrand carBrand, Instant startDate, Instant finishDate);

    List<Driving> findAllByIdIn(Set<Long> drivingsToAdd);
}
