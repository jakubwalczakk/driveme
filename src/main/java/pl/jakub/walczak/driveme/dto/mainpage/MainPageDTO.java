package pl.jakub.walczak.driveme.dto.mainpage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.jakub.walczak.driveme.dto.course.CourseDTO;
import pl.jakub.walczak.driveme.dto.user.UserBasicDTO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MainPageDTO {

    private CourseDTO course;
    private UserBasicDTO user;
}
