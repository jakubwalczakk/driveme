package pl.jakub.walczak.driveme.model.user;

import pl.jakub.walczak.driveme.model.user.User;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "instructors")
public class Instructor extends User {

    @Column(name = "available_hours")
    private int availableHours;
    @Column(name = "taken_hours")
    private int takenHours;

    public Instructor(){

    }

    public Instructor(String firstName, String lastName, String phoneNumber, String email, int availableHours, int
            takenHours) {
        super(firstName, lastName, phoneNumber, email);
        this.availableHours = availableHours;
        this.takenHours = takenHours;
    }

    public int getAvailableHours() {
        return availableHours;
    }

    public void setAvailableHours(int availableHours) {
        this.availableHours = availableHours;
    }

    public int getTakenHours() {
        return takenHours;
    }

    public void setTakenHours(int takenHours) {
        this.takenHours = takenHours;
    }
}
