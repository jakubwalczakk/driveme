package pl.jakub.walczak.driveme.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.model.user.Student;
import pl.jakub.walczak.driveme.repos.user.StudentRepository;
import pl.jakub.walczak.driveme.utils.PasswordEncryptor;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public Student save(Student student) {
        try {
            student.setPassword(PasswordEncryptor.getHashedPassword(student.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return studentRepository.save(student);
    }

    public Iterable<Student> findAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> findStudentByFirstName(String firstName) {
        return studentRepository.findStudentByFirstName(firstName);
    }
}
