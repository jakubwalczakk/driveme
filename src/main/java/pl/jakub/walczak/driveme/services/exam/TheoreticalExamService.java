package pl.jakub.walczak.driveme.services.exam;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.exam.TheoreticalExamDTO;
import pl.jakub.walczak.driveme.mappers.exam.TheoreticalExamMapper;
import pl.jakub.walczak.driveme.model.exam.TheoreticalExam;
import pl.jakub.walczak.driveme.repos.exam.TheoreticalExamRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TheoreticalExamService {

    private TheoreticalExamRepository theoreticalExamRepository;
    private TheoreticalExamMapper theoreticalExamMapper;

    @Autowired
    public TheoreticalExamService(TheoreticalExamRepository theoreticalExamRepository, TheoreticalExamMapper theoreticalExamMapper) {
        this.theoreticalExamRepository = theoreticalExamRepository;
        this.theoreticalExamMapper = theoreticalExamMapper;
    }

    // -- methods for controller --
    public TheoreticalExam addTheoreticalExam(TheoreticalExamDTO theoreticalExamDTO) {
        log.info("Adding new TheoreticalExam...");
        TheoreticalExam theoreticalExam = mapDTOToModel(theoreticalExamDTO, TheoreticalExam.builder().build());
        theoreticalExam.setActive(true);
        return theoreticalExamRepository.save(theoreticalExam);
    }

    public void deleteTheoreticalExam(Long id) {
        log.info("Deleting the TheoreticalExam with id = " + id);
        Optional<TheoreticalExam> theoreticalExamToDelete = theoreticalExamRepository.findById(id);
        if (theoreticalExamToDelete.isPresent()) {
            TheoreticalExam theoreticalExam = theoreticalExamToDelete.get();
            theoreticalExam.setActive(false);
            theoreticalExamRepository.save(theoreticalExam);
        } else {
            throw new NoSuchElementException("Cannot DELETE TheoreticalExam with given id = " + id);
        }
    }

    public TheoreticalExamDTO getExam(Long id) {
        log.info("Getting the TheoreticalExam with id = " + id);
        Optional<TheoreticalExam> optionalTheoreticalExam = theoreticalExamRepository.findById(id);
        if (optionalTheoreticalExam.isPresent()) {
            return mapModelToDTO(optionalTheoreticalExam.get(), TheoreticalExamDTO.builder().build());
        } else {
            throw new NoSuchElementException("Cannot GET TheoreticalExam with given id = " + id);
        }
    }

    public List<TheoreticalExamDTO> getTheoreticalExamsOfStudent(Long studentId) {
        log.info("Getting all TheoreticalExams of Student with given id = " + studentId);
        List<TheoreticalExam> listOfTheoreticalExams =
                theoreticalExamRepository.findAllByStudentIdOrderByPassedDescDateOfExamDesc(studentId);
        return listOfTheoreticalExams.stream()
                .map(exam -> mapModelToDTO(exam, TheoreticalExamDTO.builder().build())).collect(Collectors.toList());
    }

    public List<TheoreticalExamDTO> getAll() {
        log.info("Getting all TheoreticalExams");
        return findAll().stream().map(exam -> mapModelToDTO(exam, TheoreticalExamDTO.builder().build())).collect(Collectors.toList());
    }

    // -- dao methods --
    public List<TheoreticalExam> findAll() {
        return theoreticalExamRepository.findAll();
    }

    public List<TheoreticalExam> findAllById(Set<Long> theoreticalExamsToAdd) {
        return theoreticalExamRepository.findAllById(theoreticalExamsToAdd);
    }

    // -- mapper methods --
    public TheoreticalExamDTO mapModelToDTO(TheoreticalExam model, TheoreticalExamDTO dto) {
        return theoreticalExamMapper.mapModelToDTO(model, dto);
    }

    public TheoreticalExam mapDTOToModel(TheoreticalExamDTO dto, TheoreticalExam model) {
        if (dto.getId() != null) {
            Optional<TheoreticalExam> optionalExam = theoreticalExamRepository.findById(dto.getId());
            if (optionalExam.isPresent()) {
                model = optionalExam.get();
            }
        }
        model = theoreticalExamMapper.mapDTOToModel(dto, model);
        return theoreticalExamRepository.save(model);
    }
}
