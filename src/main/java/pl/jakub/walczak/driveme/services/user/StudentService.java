package pl.jakub.walczak.driveme.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.user.StudentDTO;
import pl.jakub.walczak.driveme.dto.user.StudentRegistrationDTO;
import pl.jakub.walczak.driveme.mappers.user.RegistrationMapper;
import pl.jakub.walczak.driveme.mappers.user.StudentMapper;
import pl.jakub.walczak.driveme.model.user.Student;
import pl.jakub.walczak.driveme.repos.user.StudentRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Student student = registrationMapper.mapRegistrationDTOToStudent(studentRegistrationDTO);
        return studentRepository.save(student);
    }

    public Student activateStudent(Long id) {
        Optional<Student> studentToActivate = studentRepository.findById(id);
        if (studentToActivate.isPresent()) {
            Student student = studentToActivate.get();
            student.setActive(true);
            return studentRepository.save(student);
        } else {
            throw new NoSuchElementException();
        }
    }

    public void deleteStudent(Long id) {
        Optional<Student> studentToDelete = studentRepository.findById(id);
        if (studentToDelete.isPresent()) {
            Student student = studentToDelete.get();
            student.setActive(false);
            studentRepository.save(student);
        } else {
            throw new NoSuchElementException();
        }
    }

    public StudentDTO getStudent(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            return mapModelToDTO(optionalStudent.get(), StudentDTO.builder().build());
        } else {
            throw new NoSuchElementException();
        }
    }

    public List<StudentDTO> getAll() {
        return findAll().stream().map(student -> mapModelToDTO(student, StudentDTO.builder().build())).collect(Collectors.toList());
    }

    // -- dao methods --
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> findStudentByName(String name) {
        return studentRepository.findStudentByName(name);
    }

    // -- mapper methods --
    public StudentDTO mapModelToDTO(Student model, StudentDTO dto) {
        return studentMapper.mapModelToDTO(model, dto);
    }

    public Student mapDTOToModel(StudentDTO dto, Student model) {
        if (dto.getId() != null) {
            Optional<Student> optionalStudent = studentRepository.findById(dto.getId());
            if (optionalStudent.isPresent()) {
                model = optionalStudent.get();
            }
        }
        model = studentMapper.mapDTOToModel(dto, model);
        return studentRepository.save(model);
    }
}
