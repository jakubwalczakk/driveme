package pl.jakub.walczak.driveme.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.model.user.Student;
import pl.jakub.walczak.driveme.repos.user.StudentRepository;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;


    public Iterable<Student> findAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> findStudentByName(String name) {
        return studentRepository.findStudentByName(name);
    }
}
