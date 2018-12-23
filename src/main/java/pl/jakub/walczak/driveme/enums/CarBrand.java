package pl.jakub.walczak.driveme.enums;

import java.util.HashMap;
import java.util.Map;

public enum CarBrand {

    TOYOTA("TOYOTA"),
    FIAT("FIAT"),
    NISSAN("NISSAN"),
    OPEL("OPEL"),
    RENAULT("RENAULT"),
    KIA("KIA"),
    HYUNDAI("HYUNDAI"),
    MITSUBISHI("MITSUBISHI"),
    DEFAULT("Brak informacji");

    private static final Map<String, CarBrand> map = new HashMap<>(values().length, 1);

    static {
        for (CarBrand c : values()) map.put(c.value, c);
    }

    private final String value;

    CarBrand(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CarBrand of(String carBrand) {
        CarBrand result = map.get(carBrand);
        if (result == null) {
            throw new IllegalArgumentException("Invalid data for CarBrand : " + carBrand);
        }
        return result;
    }
}
