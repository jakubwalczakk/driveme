package pl.jakub.walczak.driveme.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.model.user.Instructor;
import pl.jakub.walczak.driveme.repos.user.InstructorRepository;

import java.util.List;

@Service
public class InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    public List<Instructor> findAll() {
        return instructorRepository.findAll();
    }
}
