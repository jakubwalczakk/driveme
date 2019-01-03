package pl.jakub.walczak.driveme.services.event.exam;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.event.exam.TheoreticalExamDTO;
import pl.jakub.walczak.driveme.mappers.event.exam.TheoreticalExamMapper;
import pl.jakub.walczak.driveme.model.event.exam.TheoreticalExam;
import pl.jakub.walczak.driveme.model.user.User;
import pl.jakub.walczak.driveme.repos.event.exam.TheoreticalExamRepository;
import pl.jakub.walczak.driveme.utils.AuthenticationUtil;

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
    private AuthenticationUtil authenticationUtil;

    @Autowired
    public TheoreticalExamService(TheoreticalExamRepository theoreticalExamRepository, TheoreticalExamMapper theoreticalExamMapper, AuthenticationUtil authenticationUtil) {
        this.theoreticalExamRepository = theoreticalExamRepository;
        this.theoreticalExamMapper = theoreticalExamMapper;
        this.authenticationUtil = authenticationUtil;
    }

    // -- methods for controller --
    public TheoreticalExam addTheoreticalExam(TheoreticalExamDTO theoreticalExamDTO) {
        log.info("Adding new TheoreticalExam...");
        TheoreticalExam theoreticalExam = mapDTOToModel(theoreticalExamDTO, TheoreticalExam.builder().build());
        return theoreticalExamRepository.save(theoreticalExam);
    }

    public void deleteTheoreticalExam(Long id) {
        log.info("Deleting the TheoreticalExam with id = " + id);
        Optional<TheoreticalExam> theoreticalExamToDelete = theoreticalExamRepository.findById(id);
        theoreticalExamRepository.delete(theoreticalExamToDelete.orElseThrow(() ->
                new NoSuchElementException("Cannot DELETE TheoreticalExam with given id = " + id)));
    }

    public TheoreticalExamDTO getExam(Long id) {
        log.info("Getting the TheoreticalExam with id = " + id);
        Optional<TheoreticalExam> optionalTheoreticalExam = theoreticalExamRepository.findById(id);
        return mapModelToDTO(optionalTheoreticalExam.orElseThrow(() ->
                new NoSuchElementException("Cannot GET TheoreticalExam with given id = " + id)), TheoreticalExamDTO.builder().build());
    }

    public List<TheoreticalExamDTO> getTheoreticalExamsOfStudent() {
        User currentLoggedUser = authenticationUtil.getCurrentLoggedUser();
        Long currentLoggedUserId = currentLoggedUser.getId();
        log.info("Getting all TheoreticalExams of current logged Student with id = " + currentLoggedUserId);
        List<TheoreticalExam> listOfTheoreticalExams =
                theoreticalExamRepository.findAllByStudentIdOrderByPassedDescStartDateDesc(currentLoggedUserId);
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
            model = optionalExam.orElse(model);
        }
        model = theoreticalExamMapper.mapDTOToModel(dto, model);
        return theoreticalExamRepository.save(model);
    }
}
