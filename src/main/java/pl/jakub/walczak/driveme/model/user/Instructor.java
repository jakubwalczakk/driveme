package pl.jakub.walczak.driveme.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@SuperBuilder
@NoArgsConstructor
@Entity(name = "instructors")
public class Instructor extends User {

    private Integer workingHours;
    private Integer availableHours;
    private Integer takenHours;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition="mediumblob")
    private byte[] instructorPhoto;
}
