package pl.jakub.walczak.driveme.enums;

import java.util.HashMap;
import java.util.Map;

public enum GasType {
    PETROL("Benzyna"),
    GAS("Gaz"),
    OIL("Ropa naftowa"),
    DEFAULT("Brak informacji");

    private static final Map<String, GasType> map = new HashMap<>(values().length, 1);

    static {
        for (GasType c : values()) map.put(c.value, c);
    }

    private final String value;

    GasType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static GasType of(String gasType) {
        GasType result = map.get(gasType);
        if (result == null) {
            throw new IllegalArgumentException("Invalid data for GasType : " + gasType);
        }
        return result;
    }

}
