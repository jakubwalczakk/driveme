package pl.jakub.walczak.driveme.services.event.exam;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.event.exam.PracticalExamDTO;
import pl.jakub.walczak.driveme.dto.event.exam.RateExamDTO;
import pl.jakub.walczak.driveme.mappers.event.exam.PracticalExamMapper;
import pl.jakub.walczak.driveme.model.event.exam.PracticalExam;
import pl.jakub.walczak.driveme.model.user.User;
import pl.jakub.walczak.driveme.repos.event.exam.PracticalExamRepository;
import pl.jakub.walczak.driveme.utils.AuthenticationUtil;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PracticalExamService {

    private PracticalExamRepository practicalExamRepository;
    private PracticalExamMapper practicalExamMapper;
    private AuthenticationUtil authenticationUtil;

    @Autowired
    public PracticalExamService(PracticalExamRepository practicalExamRepository, PracticalExamMapper practicalExamMapper, AuthenticationUtil authenticationUtil) {
        this.practicalExamRepository = practicalExamRepository;
        this.practicalExamMapper = practicalExamMapper;
        this.authenticationUtil = authenticationUtil;
    }

    // -- methods for controller
    public PracticalExam addPracticalExam(PracticalExamDTO practicalExamDTO) {
        log.info("Adding new PracticalExam...");
        PracticalExam practicalExam = mapDTOToModel(practicalExamDTO, PracticalExam.builder().build());
        return practicalExamRepository.save(practicalExam);
    }

    public PracticalExamDTO rateExam(RateExamDTO rateExamDTO) {
        log.info("Rating a Practical Exam with id = " + rateExamDTO.getExamId());
        Optional<PracticalExam> optionalPracticalExam = practicalExamRepository.findById(rateExamDTO.getExamId());
        if (optionalPracticalExam.isPresent()) {
            PracticalExam practicalExam = optionalPracticalExam.get();
            practicalExam.setPassed(rateExamDTO.getPassed());
            practicalExamRepository.save(practicalExam);
            return mapModelToDTO(practicalExam, PracticalExamDTO.builder().build());
        }
        throw new NoSuchElementException("Cannot find a Practical Exam with given id = " + rateExamDTO.getExamId());
    }

    public void deletePracticalExam(Long id) {
        log.info("Deleting the PracticalExam with id = " + id);
        Optional<PracticalExam> practicalExamToDelete = practicalExamRepository.findById(id);
        practicalExamRepository.delete(practicalExamToDelete.orElseThrow(() ->
                new NoSuchElementException("Cannot DELETE PracticalExam with given id = " + id)));
    }

    public PracticalExamDTO getPracticalExam(Long id) {
        log.info("Getting the PracticalExam with id = " + id);
        Optional<PracticalExam> optionalPracticalExam = practicalExamRepository.findById(id);
        return mapModelToDTO(optionalPracticalExam.orElseThrow(() ->
                        new NoSuchElementException("Cannot GET PracticalExam with given id = " + id)),
                PracticalExamDTO.builder().build());
    }

    public PracticalExamDTO getPracticalExamOfStudent() {
        User currentLoggedUser = authenticationUtil.getCurrentLoggedUser();
        Long currentLoggedUserId = currentLoggedUser.getId();
        log.info("Getting the PracticalExam of current logged Student with id = " + currentLoggedUserId);
        Optional<PracticalExam> optionalPracticalExam = practicalExamRepository.findByStudentId(currentLoggedUserId);
        return mapModelToDTO(optionalPracticalExam.orElseThrow(() ->
                        new NoSuchElementException("Cannot GET PracticalExam of Student with id = " + currentLoggedUserId)),
                PracticalExamDTO.builder().build());
    }

    public List<PracticalExamDTO> getPracticalExamsOfInstructor() {
        User currentLoggedUser = authenticationUtil.getCurrentLoggedUser();
        Long currentLoggedUserId = currentLoggedUser.getId();
        log.info("Getting all PracticalExams of current logged Instructor with id = " + currentLoggedUserId);
        List<PracticalExam> practicalExamsOfInstructor = practicalExamRepository.findAllByInstructorId(currentLoggedUserId);
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
            model = optionalExam.orElse(model);
        }
        model = practicalExamMapper.mapDTOToModel(dto, model);
        return practicalExamRepository.save(model);
    }
}
