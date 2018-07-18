package pl.jakub.walczak.driveme.services.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.model.exam.Exam;
import pl.jakub.walczak.driveme.repos.exam.ExamRepository;

@Service
public class ExamService {


    @Autowired
    private ExamRepository examRepository;

    public Exam save(Exam exam) {
        return examRepository.save(exam);
    }
}
