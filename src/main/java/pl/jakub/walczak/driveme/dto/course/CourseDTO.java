package pl.jakub.walczak.driveme.dto.course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.jakub.walczak.driveme.dto.exam.PracticalExamDTO;
import pl.jakub.walczak.driveme.dto.exam.TheoreticalExamDTO;
import pl.jakub.walczak.driveme.dto.user.StudentDTO;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {

    private Long id;
    private LocalDate startDate;
    private Integer takenDrivingHours;
    private StudentDTO student;
    private PracticalExamDTO practicalExam;
    private Set<TheoreticalExamDTO> theoreticalExams;
}
