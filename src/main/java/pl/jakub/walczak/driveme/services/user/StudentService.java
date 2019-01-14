package pl.jakub.walczak.driveme.services.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.user.StudentDTO;
import pl.jakub.walczak.driveme.dto.user.StudentRegistrationDTO;
import pl.jakub.walczak.driveme.mappers.user.RegistrationMapper;
import pl.jakub.walczak.driveme.mappers.user.StudentMapper;
import pl.jakub.walczak.driveme.model.user.Student;
import pl.jakub.walczak.driveme.repos.user.StudentRepository;
import pl.jakub.walczak.driveme.security.ApiResponse;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StudentService {

    private StudentRepository studentRepository;
    private StudentMapper studentMapper;
    private RegistrationMapper registrationMapper;

    @Autowired
    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper,
                          RegistrationMapper registrationMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.registrationMapper = registrationMapper;
    }

    // -- methods for controller --
    public Student createStudent(StudentRegistrationDTO studentRegistrationDTO) {
        log.info("Creating new Student...");
        Student student = registrationMapper.mapRegistrationDTOToStudent(studentRegistrationDTO);
        return studentRepository.save(student);
    }

    public Student activateStudent(Long id) {
        log.info("Activating Student with id = " + id);
        Optional<Student> studentToActivate = studentRepository.findById(id);
        if (studentToActivate.isPresent()) {
            Student student = studentToActivate.get();
            student.setActive(true);
//            student.setCourse(new Course());
            return studentRepository.save(student);
        } else {
            throw new NoSuchElementException("Cannot ACTIVATE Student with given id = " + id);
        }
    }

    public ApiResponse updateStudent(StudentDTO studentDTO) {
        Optional<Student> optionalStudent = studentRepository.findById(studentDTO.getId());
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student = mapDTOToModel(studentDTO, student);
            studentRepository.save(student);
            return ApiResponse.builder().success(true).message("Student successfully updated").build();
        } else {
            log.warn("Cannot find student with given id = " + studentDTO.getId());
            throw new NoSuchElementException("Cannot find student with given id = " + studentDTO.getId());
        }
    }

    public void deleteStudent(Long id) {
        log.info("Deleting the Student with id = " + id);
        Optional<Student> studentToDelete = studentRepository.findById(id);
        if (studentToDelete.isPresent()) {
            Student student = studentToDelete.get();
            student.setActive(false);
            studentRepository.save(student);
        } else {
            throw new NoSuchElementException("Cannot DELETE Student with given id = " + id);
        }
    }

    public StudentDTO getStudent(Long id) {
        log.info("Getting the Student with id = " + id);
        Optional<Student> optionalStudent = studentRepository.findById(id);
        return mapModelToDTO(optionalStudent.orElseThrow(() ->
                new NoSuchElementException("Cannot GET Student with given id = " + id)), StudentDTO.builder().build());
    }

    public List<StudentDTO> getAllOrderByRegistrationDate() {
        log.info("Getting all Students ordered by registration date desc");
        return studentRepository.findAll().stream().sorted((i1, i2) -> i2.getRegistrationDate().compareTo(i1.getRegistrationDate()))
                .map(student ->
                        mapModelToDTO(student, StudentDTO.builder().build())).collect(Collectors.toList());
    }

    // -- dao methods --
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    // -- mapper methods --
    public StudentDTO mapModelToDTO(Student model, StudentDTO dto) {
        return studentMapper.mapModelToDTO(model, dto);
    }

    public Student mapDTOToModel(StudentDTO dto, Student model) {
        if (dto.getId() != null) {
            Optional<Student> optionalStudent = studentRepository.findById(dto.getId());
            model = optionalStudent.orElse(model);
        }
        model = studentMapper.mapDTOToModel(dto, model);
        return studentRepository.save(model);
    }
}
