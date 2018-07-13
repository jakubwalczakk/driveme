package pl.jakub.walczak.driveme.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.repos.StudentRepository;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;
}
