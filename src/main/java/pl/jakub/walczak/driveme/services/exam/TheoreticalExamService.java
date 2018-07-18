package pl.jakub.walczak.driveme.services.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.repos.exam.TheoreticalExamRepository;

@Service
public class TheoreticalExamService {

    @Autowired
    private TheoreticalExamRepository theoreticalExamRepository;
}
