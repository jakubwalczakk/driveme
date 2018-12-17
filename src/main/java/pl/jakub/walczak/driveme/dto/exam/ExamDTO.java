package pl.jakub.walczak.driveme.dto.exam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.jakub.walczak.driveme.dto.user.UserBasicDTO;

import java.time.Instant;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ExamDTO {

    private Long id;
    private String dateOfExam;
    private Boolean active;
    private Boolean passed;
    private UserBasicDTO student;
}
