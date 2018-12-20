package pl.jakub.walczak.driveme.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@SuperBuilder
@NoArgsConstructor
@ToString(exclude = "password")
@Entity(name = "instructors")
public class Instructor extends User {

    private Integer workingHours;
    private Integer availableHours = 0;
    private Integer takenHours = 0;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "mediumblob")
    private byte[] instructorPhoto;
}
