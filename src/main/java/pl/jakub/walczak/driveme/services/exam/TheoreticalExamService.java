package pl.jakub.walczak.driveme.services.exam;

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
        TheoreticalExam theoreticalExam = mapDTOToModel(theoreticalExamDTO, TheoreticalExam.builder().build());
        return theoreticalExamRepository.save(theoreticalExam);
    }

    public void deleteExam(Long id) {
        Optional<TheoreticalExam> theoreticalExamToDelete = theoreticalExamRepository.findById(id);
        if (theoreticalExamToDelete.isPresent()) {
            TheoreticalExam theoreticalExam = theoreticalExamToDelete.get();
            theoreticalExam.setActive(false);
            theoreticalExamRepository.save(theoreticalExam);
        } else {
            throw new NoSuchElementException();
        }
    }

    public TheoreticalExamDTO getExam(Long id) {
        Optional<TheoreticalExam> optionalTheoreticalExam = theoreticalExamRepository.findById(id);
        if (optionalTheoreticalExam.isPresent()) {
            return mapModelToDTO(optionalTheoreticalExam.get(), TheoreticalExamDTO.builder().build());
        } else {
            throw new NoSuchElementException();
        }
    }

    public List<TheoreticalExamDTO> getAll() {
        return findAll().stream().map(exam -> mapModelToDTO(exam, TheoreticalExamDTO.builder().build())).collect(Collectors.toList());
    }

    // -- dao methods --
    public List<TheoreticalExam> findAll() {
        return theoreticalExamRepository.findAll();
    }

    public Set<TheoreticalExam> findAllById(Set<Long> theoreticalExamsToAdd) {
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
