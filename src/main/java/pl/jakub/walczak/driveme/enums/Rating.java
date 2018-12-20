package pl.jakub.walczak.driveme.enums;

import java.util.HashMap;
import java.util.Map;

public enum Rating {

    DISAPPOINTING("Rozczarowująco"),
    AVERAGE("Przeciętnie"),
    OK("W porządku"),
    GREAT("Świetnie"),
    MASTER("Mistrzowsko"),
    DEFAULT("Brak informacji");

    private static final Map<String, Rating> map = new HashMap<>(values().length, 1);

    static {
        for (Rating c : values()) map.put(c.value, c);
    }

    private final String value;

    Rating(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Rating of(String rating) {
        Rating result = map.get(rating);
        if (result == null) {
            throw new IllegalArgumentException("Invalid data for GasType : " + rating);
        }
        return result;
    }
}
