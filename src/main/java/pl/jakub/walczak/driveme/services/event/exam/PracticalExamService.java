package pl.jakub.walczak.driveme.services.event.exam;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.event.exam.PracticalExamDTO;
import pl.jakub.walczak.driveme.mappers.event.exam.PracticalExamMapper;
import pl.jakub.walczak.driveme.model.event.exam.PracticalExam;
import pl.jakub.walczak.driveme.repos.event.exam.PracticalExamRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PracticalExamService {

    private PracticalExamRepository practicalExamRepository;
    private PracticalExamMapper practicalExamMapper;

    @Autowired
    public PracticalExamService(PracticalExamRepository practicalExamRepository, PracticalExamMapper practicalExamMapper) {
        this.practicalExamRepository = practicalExamRepository;
        this.practicalExamMapper = practicalExamMapper;
    }

    // -- methods for controller
    public PracticalExam addPracticalExam(PracticalExamDTO practicalExamDTO) {
        log.info("Adding new PracticalExam...");
        PracticalExam practicalExam = mapDTOToModel(practicalExamDTO, PracticalExam.builder().build());
        return practicalExamRepository.save(practicalExam);
    }

    public void deletePracticalExam(Long id) {
        log.info("Deleting the PracticalExam with id = " + id);
        Optional<PracticalExam> practicalExamToDelete = practicalExamRepository.findById(id);
        if (practicalExamToDelete.isPresent()) {
            PracticalExam practicalExam = practicalExamToDelete.get();
            practicalExamRepository.delete(practicalExam);
        } else {
            throw new NoSuchElementException("Cannot DELETE PracticalExam with given id = " + id);
        }
    }

    public PracticalExamDTO getPracticalExam(Long id) {
        log.info("Getting the PracticalExam with id = " + id);
        Optional<PracticalExam> optionalPracticalExam = practicalExamRepository.findById(id);
        if (optionalPracticalExam.isPresent()) {
            return mapModelToDTO(optionalPracticalExam.get(), PracticalExamDTO.builder().build());
        } else {
            throw new NoSuchElementException("Cannot GET PracticalExam with given id = " + id);
        }
    }

    public PracticalExamDTO getPracticalExamOfStudent(Long studentId) {
        log.info("Getting the PracticalExam of Student with id = " + studentId);
        Optional<PracticalExam> optionalPracticalExam = practicalExamRepository.findByStudentId(studentId);
        if (optionalPracticalExam.isPresent()) {
            return mapModelToDTO(optionalPracticalExam.get(), PracticalExamDTO.builder().build());
        } else {
            throw new NoSuchElementException("Cannot GET PracticalExam of Student with given id = " + studentId);
        }
    }

    public List<PracticalExamDTO> getPracticalExamsOfInstructor(Long instructorId) {
        log.info("Getting all PracticalExams of Instructor with id = " + instructorId);
        List<PracticalExam> practicalExamsOfInstructor = practicalExamRepository.findAllByInstructorId(instructorId);
        return practicalExamsOfInstructor.stream().map(exam -> mapModelToDTO(exam, PracticalExamDTO.builder().build()))
                .collect(Collectors.toList());
    }

    public List<PracticalExamDTO> getAll() {
        log.info("Getting all PracticalExams");
        return findAll().stream().map(exam -> mapModelToDTO(exam, PracticalExamDTO.builder().build())).collect(Collectors.toList());
    }

    // -- dao methods --
    public Optional<PracticalExam> findById(Long id) {
        return practicalExamRepository.findById(id);
    }

    public List<PracticalExam> findAll() {
        return practicalExamRepository.findAll();
    }

    // -- mapper methods --
    public PracticalExamDTO mapModelToDTO(PracticalExam model, PracticalExamDTO dto) {
        return model == null ? null : practicalExamMapper.mapModelToDTO(model, dto);
    }

    public PracticalExam mapDTOToModel(PracticalExamDTO dto, PracticalExam model) {
        if (dto.getId() != null) {
            Optional<PracticalExam> optionalExam = practicalExamRepository.findById(dto.getId());
            if (optionalExam.isPresent()) {
                model = optionalExam.get();
            }
        }
        model = practicalExamMapper.mapDTOToModel(dto, model);
        return practicalExamRepository.save(model);
    }
}
