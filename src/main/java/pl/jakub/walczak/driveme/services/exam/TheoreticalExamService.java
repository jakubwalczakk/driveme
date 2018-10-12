package pl.jakub.walczak.driveme.services.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.exam.TheoreticalExamDTO;
import pl.jakub.walczak.driveme.mappers.exam.TheoreticalExamMapper;
import pl.jakub.walczak.driveme.model.exam.TheoreticalExam;
import pl.jakub.walczak.driveme.repos.exam.TheoreticalExamRepository;

import java.util.List;
import java.util.Optional;

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

    // -- dao methods --
    public List<TheoreticalExam> findAll() {
        return theoreticalExamRepository.findAll();
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
