package pl.jakub.walczak.driveme.enums;

import java.util.HashMap;
import java.util.Map;

public enum UserRole {

    ADMIN("Administrator"),
    INSTRUCTOR("Instruktor"),
    STUDENT("Kursant"),
    DEFAULT("Brak informacji");

    private static final Map<String, UserRole> map = new HashMap<>(values().length, 1);

    static {
        for (UserRole c : values()) map.put(c.value, c);
    }

    private final String value;

    UserRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static UserRole of(String userRole) {
        UserRole result = map.get(userRole);
        if (result == null) {
            throw new IllegalArgumentException("Invalid data for UserRole : " + userRole);
        }
        return result;
    }
}
