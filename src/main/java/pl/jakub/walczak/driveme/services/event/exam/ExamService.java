package pl.jakub.walczak.driveme.services.event.exam;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.event.exam.ExamDTO;
import pl.jakub.walczak.driveme.mappers.event.exam.ExamMapper;
import pl.jakub.walczak.driveme.model.event.exam.Exam;
import pl.jakub.walczak.driveme.repos.event.exam.ExamRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ExamService {

    private ExamRepository examRepository;
    private ExamMapper examMapper;

    @Autowired
    public ExamService(ExamRepository examRepository, ExamMapper examMapper) {
        this.examRepository = examRepository;
        this.examMapper = examMapper;
    }

    // -- methods for controller --
    public Exam addExam(ExamDTO examDTO) {
        log.info("Adding new Exam...");
        Exam exam = mapDTOToModel(examDTO, Exam.builder().build());
        return examRepository.save(exam);
    }

    public void deleteExam(Long id) {
        log.info("Deleting the Exam with id = " + id);
        Optional<Exam> examToDelete = examRepository.findById(id);
        examRepository.delete(examToDelete.orElseThrow(() ->
                new NoSuchElementException("Cannot DELETE Exam with given id = " + id)));
    }

    public ExamDTO getExam(Long id) {
        log.info("Getting the Exam with id = " + id);
        Optional<Exam> optionalExam = examRepository.findById(id);
        return mapModelToDTO(optionalExam.orElseThrow(() ->
                new NoSuchElementException("Cannot GET Exam with given id = " + id)), ExamDTO.builder().build());
    }

    public List<ExamDTO> getAll() {
        log.info("Getting all Exams");
        return findAll().stream().map(exam -> mapModelToDTO(exam, ExamDTO.builder().build())).collect(Collectors.toList());
    }

    // -- dao methods --
    public List<Exam> findAll() {
        return examRepository.findAll();
    }

    // -- mapper methods --
    public ExamDTO mapModelToDTO(Exam model, ExamDTO dto) {
        return examMapper.mapModelToDTO(model, dto);
    }

    public Exam mapDTOToModel(ExamDTO dto, Exam model) {
        if (dto.getId() != null) {
            Optional<Exam> optionalExam = examRepository.findById(dto.getId());
            model = optionalExam.orElse(model);
        }
        model = examMapper.mapDTOToModel(dto, model);
        return examRepository.save(model);
    }
}
