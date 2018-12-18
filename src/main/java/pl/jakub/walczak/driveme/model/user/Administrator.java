package pl.jakub.walczak.driveme.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Data
@SuperBuilder
@NoArgsConstructor
@Entity(name = "administrators")
public class Administrator extends User {

}
