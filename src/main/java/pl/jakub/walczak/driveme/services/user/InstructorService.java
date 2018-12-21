package pl.jakub.walczak.driveme.services.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.user.InstructorDTO;
import pl.jakub.walczak.driveme.dto.user.InstructorRegistrationDTO;
import pl.jakub.walczak.driveme.mappers.user.InstructorMapper;
import pl.jakub.walczak.driveme.mappers.user.RegistrationMapper;
import pl.jakub.walczak.driveme.model.user.Instructor;
import pl.jakub.walczak.driveme.repos.user.InstructorRepository;
import pl.jakub.walczak.driveme.security.ApiResponse;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class InstructorService {

    private InstructorRepository instructorRepository;
    private InstructorMapper instructorMapper;
    private RegistrationMapper registrationMapper;

    @Autowired
    public InstructorService(InstructorRepository instructorRepository, InstructorMapper instructorMapper,
                             RegistrationMapper registrationMapper) {
        this.instructorRepository = instructorRepository;
        this.instructorMapper = instructorMapper;
        this.registrationMapper = registrationMapper;
    }

    // -- methods for controller --
    public Instructor createInstructor(InstructorRegistrationDTO instructorRegistrationDTO) {
        log.info("Creating new Instructor...");
        Instructor instructor = registrationMapper.mapRegistrationDTOToInstructor(instructorRegistrationDTO);
        return instructorRepository.save(instructor);
    }

    public Instructor activateInstructor(Long id) {
        log.info("Activating the Instructor with id = " + id);
        Optional<Instructor> instructorToActivate = instructorRepository.findById(id);
        if (instructorToActivate.isPresent()) {
            Instructor instructor = instructorToActivate.get();
            instructor.setActive(true);
            return instructorRepository.save(instructor);
        } else {
            throw new NoSuchElementException("Cannot ACTIVATE Instructor with given id = " + id);
        }
    }


    public ApiResponse updateInstructor(InstructorDTO instructorDTO) {
        Optional<Instructor> optionalInstructor = instructorRepository.findById(instructorDTO.getId());
        if (optionalInstructor.isPresent()) {
            Instructor instructor = optionalInstructor.get();
            instructor = mapDTOToModel(instructorDTO, instructor);
            instructorRepository.save(instructor);
            return ApiResponse.builder().success(true).message("Instructor successfully updated").build();

        } else {
            log.warn("Cannot find instructor with given id = " + instructorDTO.getId());
            throw new NoSuchElementException("Cannot find instructor with given id = " + instructorDTO.getId());
        }
    }

    public void deleteInstructor(Long id) {
        log.info("Deleting the Instructor with id = " + id);
        Optional<Instructor> instructorToDelete = instructorRepository.findById(id);
        if (instructorToDelete.isPresent()) {
            Instructor instructor = instructorToDelete.get();
            instructor.setActive(false);
            instructorRepository.save(instructor);
        } else {
            throw new NoSuchElementException("Cannot DELETE Instructor with given id = " + id);
        }
    }

    public InstructorDTO getInstructor(Long id) {
        log.info("Getting Instructor with id = " + id);
        Optional<Instructor> optionalInstructor = instructorRepository.findById(id);
        if (optionalInstructor.isPresent()) {
            return mapModelToDTO(optionalInstructor.get(), InstructorDTO.builder().build());
        } else {
            throw new NoSuchElementException("Cannot GET instructor with given id = " + id);
        }
    }

    public List<InstructorDTO> getAll() {
        log.info("Getting all instructors");
        return findAll().stream().map(instructor -> mapModelToDTO(instructor, InstructorDTO.builder().build())).collect(Collectors.toList());
    }

    // -- dao methods --
    public Optional<Instructor> findById(Long id) {
        return instructorRepository.findById(id);
    }

    public Optional<Instructor> findByEmail(String email) {
        return instructorRepository.findByEmail(email);
    }

    public List<Instructor> findAll() {
        return instructorRepository.findAll();
    }

    // -- mapper methods --
    public InstructorDTO mapModelToDTO(Instructor model, InstructorDTO dto) {
        return instructorMapper.mapModelToDTO(model, dto);
    }

    public Instructor mapDTOToModel(InstructorDTO dto, Instructor model) {
        if (dto.getId() != null) {
            Optional<Instructor> optionalInstructor = instructorRepository.findById(dto.getId());
            if (optionalInstructor.isPresent()) {
                model = optionalInstructor.get();
            }
        }
        model = instructorMapper.mapDTOToModel(dto, model);
        return instructorRepository.save(model);
    }
}
