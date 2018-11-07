package pl.jakub.walczak.driveme.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.user.InstructorDTO;
import pl.jakub.walczak.driveme.dto.user.InstructorRegistrationDTO;
import pl.jakub.walczak.driveme.mappers.user.InstructorMapper;
import pl.jakub.walczak.driveme.mappers.user.RegistrationMapper;
import pl.jakub.walczak.driveme.model.user.Instructor;
import pl.jakub.walczak.driveme.repos.user.InstructorRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Instructor instructor = registrationMapper.mapRegistrationDTOToInstructor(instructorRegistrationDTO);
        return instructorRepository.save(instructor);
    }

    public Instructor activateInstructor(Long id) {
        Optional<Instructor> instructorToActivate = instructorRepository.findById(id);
        if (instructorToActivate.isPresent()) {
            Instructor instructor = instructorToActivate.get();
            instructor.setActive(true);
            return instructorRepository.save(instructor);
        } else {
            throw new NoSuchElementException();
        }
    }

    public void deleteInstructor(Long id) {
        Optional<Instructor> instructorToDelete = instructorRepository.findById(id);
        if (instructorToDelete.isPresent()) {
            Instructor instructor = instructorToDelete.get();
            instructor.setActive(false);
            instructorRepository.save(instructor);
        } else {
            throw new NoSuchElementException();
        }
    }

    public InstructorDTO getInstructor(Long id) {
        Optional<Instructor> optionalInstructor = instructorRepository.findById(id);
        if (optionalInstructor.isPresent()) {
            return mapModelToDTO(optionalInstructor.get(), InstructorDTO.builder().build());
        } else {
            throw new NoSuchElementException();
        }
    }

    public List<InstructorDTO> getAll() {
        return findAll().stream().map(instructor -> mapModelToDTO(instructor, InstructorDTO.builder().build())).collect(Collectors.toList());
    }

    // -- dao methods --
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
