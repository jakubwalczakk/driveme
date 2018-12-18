package pl.jakub.walczak.driveme.enums;

public enum UserRole {

    ADMIN("Admin"),
    INSTRUCTOR("Instructor"),
    STUDENT("Student"),
    DEFAULT("Brak informacji");

    private String value;

    UserRole(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
