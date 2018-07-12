package pl.jakub.walczak.driveme.services;

import org.springframework.beans.factory.annotation.Autowired;
import pl.jakub.walczak.driveme.repos.StudentRepository;

public class StudentService {

    @Autowired
    StudentRepository studentRepository;
}
