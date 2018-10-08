package pl.jakub.walczak.driveme.dto.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.jakub.walczak.driveme.dto.exam.PracticalExamDTO;
import pl.jakub.walczak.driveme.dto.exam.TheoreticalExamDTO;
import pl.jakub.walczak.driveme.dto.user.StudentDTO;

import java.time.LocalDate;
import java.util.Set;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {

    private Long id;
    private LocalDate startDate;
    private Integer takenDrivingHours;
//    private StudentDTO student;
    private PracticalExamDTO practicalExam;
    private Set<TheoreticalExamDTO> theoreticalExams;
}
