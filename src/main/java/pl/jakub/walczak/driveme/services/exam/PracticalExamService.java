package pl.jakub.walczak.driveme.services.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.exam.PracticalExamDTO;
import pl.jakub.walczak.driveme.mappers.exam.PracticalExamMapper;
import pl.jakub.walczak.driveme.model.exam.PracticalExam;
import pl.jakub.walczak.driveme.repos.exam.PracticalExamRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PracticalExamService {

    private PracticalExamRepository practicalExamRepository;
    private PracticalExamMapper practicalExamMapper;

    @Autowired
    public PracticalExamService(PracticalExamRepository practicalExamRepository, PracticalExamMapper practicalExamMapper) {
        this.practicalExamRepository = practicalExamRepository;
        this.practicalExamMapper = practicalExamMapper;
    }

    public List<PracticalExam> findAll() {
        return practicalExamRepository.findAll();
    }

    public PracticalExamDTO mapModelToDTO(PracticalExam model, PracticalExamDTO dto) {
        return practicalExamMapper.mapModelToDTO(model, dto);
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
