package pl.jakub.walczak.driveme.enums;

import java.util.HashMap;
import java.util.Map;

public enum CourseStatus {

    IN_PROGRESS("W trakcie"),
    SUSPENDED("Zawieszony"),
    COMPLETED("Ukończony"),
    DEFAULT("Nie zdefiniowany");

    private static final Map<String, CourseStatus> map = new HashMap<>(values().length, 1);

    static {
        for (CourseStatus c : values()) map.put(c.value, c);
    }

    private final String value;

    CourseStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CourseStatus of(String courseStatus) {
        CourseStatus result = map.get(courseStatus);
        if (result == null) {
            throw new IllegalArgumentException("Invalid data for CourseStatus : " + courseStatus);
        }
        return result;
    }
}
