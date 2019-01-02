package pl.jakub.walczak.driveme.enums;

import java.util.HashMap;
import java.util.Map;

public enum EventType {

    PRACTICAL_EXAM("Egzamin praktyczny"),
    THEORETICAL_EXAM("Egzamin teoretyczny"),
    RESERVATION("Rezerwacja terminu"),
    DRIVING("Jazda szkoleniowa"),
    DEFAULT("Brak informacji");

    private static final Map<String, EventType> map = new HashMap<>(values().length, 1);

    static {
        for (EventType c : values()) map.put(c.value, c);
    }

    private final String value;

    EventType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static EventType of(String eventType) {
        EventType result = map.get(eventType);
        if (result == null) {
            throw new IllegalArgumentException("Invalid data for EventType : " + eventType);
        }
        return result;
    }
}
