package pl.jakub.walczak.driveme.services.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.event.DrivingDTO;
import pl.jakub.walczak.driveme.dto.event.RateDrivingDTO;
import pl.jakub.walczak.driveme.enums.Rating;
import pl.jakub.walczak.driveme.mappers.event.DrivingMapper;
import pl.jakub.walczak.driveme.model.event.Driving;
import pl.jakub.walczak.driveme.repos.event.DrivingRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DrivingService {

    private DrivingRepository drivingRepository;
    private DrivingMapper drivingMapper;

    @Autowired
    public DrivingService(DrivingRepository drivingRepository, DrivingMapper drivingMapper) {
        this.drivingRepository = drivingRepository;
        this.drivingMapper = drivingMapper;
    }

    // -- methods for controller --
    public Driving addDriving(DrivingDTO drivingDTO) {
        log.info("Adding new Driving...");
        Driving driving = mapDTOToModel(drivingDTO, Driving.builder().build());
        return drivingRepository.save(driving);
    }

    public DrivingDTO rateDriving(RateDrivingDTO rateDrivingDTO) {
        log.info("Rating a Driving with id = " + rateDrivingDTO.getDrivingId());
        Optional<Driving> optionalDriving = drivingRepository.findById(rateDrivingDTO.getDrivingId());
        try {
            Rating rating = Rating.of(rateDrivingDTO.getRating());
            if (optionalDriving.isPresent()) {
                Driving driving = optionalDriving.get();
                driving.setRating(rating);
                driving.setComment(rateDrivingDTO.getComment());
                drivingRepository.save(driving);
                return mapModelToDTO(driving, DrivingDTO.builder().build());
            }
            return null;
        } catch (IllegalArgumentException | NullPointerException e) {
            log.warn("Cannot find a value of Rating Enum = " + rateDrivingDTO.getRating());
            throw new IllegalArgumentException();
        }
    }

    public void deleteDriving(Long id) {
        log.info("Deleting the Driving with id = " + id);
        Optional<Driving> drivingToDelete = drivingRepository.findById(id);
        if (drivingToDelete.isPresent()) {
            drivingRepository.delete(drivingToDelete.get());
        } else {
            throw new NoSuchElementException("Cannot DELETE Driving with given id = " + id);
        }
    }

    public DrivingDTO getDriving(Long id) {
        log.info("Getting the Driving with id = " + id);
        Optional<Driving> optionalDriving = drivingRepository.findById(id);
        if (optionalDriving.isPresent()) {
            return mapModelToDTO(optionalDriving.get(), DrivingDTO.builder().build());
        } else {
            throw new NoSuchElementException("Cannot GET Driving with given id = " + id);
        }
    }

    public List<DrivingDTO> getDrivingsByInstructor(Long instructorId) {
        log.info("Getting the List of Drivings of Instructor with id = " + instructorId);
        List<Driving> listOfInstructorDrivings = drivingRepository.findAllByInstructorIdOrderByFinishDateDesc(instructorId);
        return listOfInstructorDrivings.stream()
                .map(driving -> mapModelToDTO(driving, DrivingDTO.builder().build())).collect(Collectors.toList());
    }

    public List<DrivingDTO> getDrivingsByStudent(Long studentId) {
        log.info("Getting the List of Drivings of Student with id = " + studentId);
        List<Driving> listOfStudentDrivings = drivingRepository.findAllByStudentIdOrderByFinishDateDesc(studentId);
        return listOfStudentDrivings.stream()
                .map(driving -> mapModelToDTO(driving, DrivingDTO.builder().build())).collect(Collectors.toList());
    }

    public List<DrivingDTO> getAll() {
        log.info("Getting all Drivings");
        return findAll().stream().map(driving -> mapModelToDTO(driving, DrivingDTO.builder().build())).collect(Collectors.toList());
    }

    // -- dao methods --
    public List<Driving> findAllById(Set<Long> drivingsToAdd) {
        return drivingRepository.findAllById(drivingsToAdd);
    }

    public List<Driving> findAll() {
        return drivingRepository.findAll();
    }

    // -- mapper methods --
    public DrivingDTO mapModelToDTO(Driving model, DrivingDTO dto) {
        return drivingMapper.mapModelToDTO(model, dto);
    }

    public Driving mapDTOToModel(DrivingDTO dto, Driving model) {
        if (dto.getId() != null) {
            Optional<Driving> optionalDriving = drivingRepository.findById(dto.getId());
            if (optionalDriving.isPresent()) {
                model = optionalDriving.get();
            }
        }
        model = drivingMapper.mapDTOToModel(dto, model);
        return drivingRepository.save(model);
    }
}
