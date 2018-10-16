package pl.jakub.walczak.driveme.enums;

public enum CourseStatus {

    IN_PROGRESS("W trakcie"),
    SUSPENDED("Zawieszony"),
    COMPLETED("Ukończony"),
    DEFAULT("Nie zdefiniowany");

    private String value;

    CourseStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
