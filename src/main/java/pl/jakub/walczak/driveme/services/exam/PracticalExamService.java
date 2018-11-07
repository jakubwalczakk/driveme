package pl.jakub.walczak.driveme.services.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.exam.PracticalExamDTO;
import pl.jakub.walczak.driveme.mappers.exam.PracticalExamMapper;
import pl.jakub.walczak.driveme.model.exam.PracticalExam;
import pl.jakub.walczak.driveme.repos.exam.PracticalExamRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

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
        PracticalExam practicalExam = mapDTOToModel(practicalExamDTO, PracticalExam.builder().build());
        practicalExam.setActive(true);
        return practicalExamRepository.save(practicalExam);
    }

    public void deletePracticalExam(Long id) {
        Optional<PracticalExam> practicalExamToDelete = practicalExamRepository.findById(id);
        if (practicalExamToDelete.isPresent()) {
            PracticalExam practicalExam = practicalExamToDelete.get();
            practicalExam.setActive(false);
            practicalExamRepository.save(practicalExam);
        } else {
            throw new NoSuchElementException();
        }
    }

    public PracticalExamDTO getPracticalExam(Long id) {
        Optional<PracticalExam> optionalPracticalExam = practicalExamRepository.findById(id);
        if (optionalPracticalExam.isPresent()) {
            return mapModelToDTO(optionalPracticalExam.get(), PracticalExamDTO.builder().build());
        } else {
            throw new NoSuchElementException();
        }
    }

    public List<PracticalExamDTO> getAll() {
        return findAll().stream().map(exam -> mapModelToDTO(exam, PracticalExamDTO.builder().build())).collect(Collectors.toList());
    }

    // -- dao methods --
    public List<PracticalExam> findAll() {
        return practicalExamRepository.findAll();
    }

    // -- mapper methods --
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
