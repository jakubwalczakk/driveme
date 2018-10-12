package pl.jakub.walczak.driveme.services.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.exam.ExamDTO;
import pl.jakub.walczak.driveme.mappers.exam.ExamMapper;
import pl.jakub.walczak.driveme.model.exam.Exam;
import pl.jakub.walczak.driveme.repos.exam.ExamRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<ExamDTO> getAll() {
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
