package pl.jakub.walczak.driveme.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.user.StudentDTO;
import pl.jakub.walczak.driveme.mappers.user.StudentMapper;
import pl.jakub.walczak.driveme.model.user.Student;
import pl.jakub.walczak.driveme.repos.user.StudentRepository;

import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;
    private StudentMapper studentMapper;

    @Autowired
    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    // -- methods for controller --

    // -- dao methods --
    public Iterable<Student> findAll() {
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
