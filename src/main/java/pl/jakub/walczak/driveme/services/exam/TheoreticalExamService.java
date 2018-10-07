package pl.jakub.walczak.driveme.services.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.model.exam.TheoreticalExam;
import pl.jakub.walczak.driveme.repos.exam.TheoreticalExamRepository;

import java.util.List;

@Service
public class TheoreticalExamService {

    @Autowired
    private TheoreticalExamRepository theoreticalExamRepository;

    public List<TheoreticalExam> findAll(){
        return theoreticalExamRepository.findAll();
    }
}
