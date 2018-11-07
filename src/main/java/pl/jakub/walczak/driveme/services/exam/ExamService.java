package pl.jakub.walczak.driveme.services.exam;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.exam.ExamDTO;
import pl.jakub.walczak.driveme.mappers.exam.ExamMapper;
import pl.jakub.walczak.driveme.model.exam.Exam;
import pl.jakub.walczak.driveme.repos.exam.ExamRepository;

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
        exam.setActive(true);
        return examRepository.save(exam);
    }

    public void deleteExam(Long id) {
        log.info("Deleting the Exam with id = " + id);
        Optional<Exam> examToDelete = examRepository.findById(id);
        if (examToDelete.isPresent()) {
            Exam exam = examToDelete.get();
            exam.setActive(false);
            examRepository.save(exam);
        } else {
            throw new NoSuchElementException("Cannot DELETE Exam with given id = " + id);
        }
    }

    public ExamDTO getExam(Long id) {
        log.info("Getting the Exam with id = " + id);
        Optional<Exam> optionalExam = examRepository.findById(id);
        if (optionalExam.isPresent()) {
            return mapModelToDTO(optionalExam.get(), ExamDTO.builder().build());
        } else {
            throw new NoSuchElementException("Cannot GET Exam with given id = " + id);
        }
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
            if (optionalExam.isPresent()) {
                model = optionalExam.get();
            }
        }
        model = examMapper.mapDTOToModel(dto, model);
        return examRepository.save(model);
    }
}
