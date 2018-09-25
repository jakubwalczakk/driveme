package pl.jakub.walczak.driveme.model.city;

import javax.persistence.*;

@Entity(name = "driving_cities")
public class DrivingCity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    public DrivingCity(){

    }

    public DrivingCity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
