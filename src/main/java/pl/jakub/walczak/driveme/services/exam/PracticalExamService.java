package pl.jakub.walczak.driveme.services.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.model.exam.PracticalExam;
import pl.jakub.walczak.driveme.repos.exam.PracticalExamRepository;

import java.util.List;

@Service
public class PracticalExamService {

    @Autowired
    private PracticalExamRepository practicalExamRepository;

    public List<PracticalExam> findAll(){
        return practicalExamRepository.findAll();
    }
}
