package pl.jakub.walczak.driveme.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.user.InstructorDTO;
import pl.jakub.walczak.driveme.mappers.user.InstructorMapper;
import pl.jakub.walczak.driveme.model.user.Instructor;
import pl.jakub.walczak.driveme.repos.user.InstructorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorService {

    private InstructorRepository instructorRepository;
    private InstructorMapper instructorMapper;

    @Autowired
    public InstructorService(InstructorRepository instructorRepository, InstructorMapper instructorMapper) {
        this.instructorRepository = instructorRepository;
        this.instructorMapper = instructorMapper;
    }

    // -- methods for controller --

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
