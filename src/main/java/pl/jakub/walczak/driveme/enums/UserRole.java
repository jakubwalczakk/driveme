package pl.jakub.walczak.driveme.enums;

public enum UserRole {

    ADMINISTRATOR("Administrator"),
    INSTRUCTOR("Instructor"),
    STUDENT("Student");

    private String value;

    UserRole(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
