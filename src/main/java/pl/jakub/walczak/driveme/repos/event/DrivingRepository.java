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
    List<Driving> findAllByInstructorIdOrderByStartDateDesc(Long instructorId);
    List<Driving> findAllByStudentIdOrderByStartDateDesc(Long studentId);
    List<Driving> findAllByInstructorEmailAndCar_Brand(String instructorEmail, CarBrand carBrand);
    List<Driving> findAllByCarBrandAndAndStartDateBefore(CarBrand carBrand, Instant startDate);
}
