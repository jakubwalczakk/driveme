package pl.jakub.walczak.driveme.services.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.repos.exam.PracticalExamRepository;

@Service
public class PracticalExamService {

    @Autowired
    private PracticalExamRepository practicalExamRepository;
}
