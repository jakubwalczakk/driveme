package pl.jakub.walczak.driveme.enums;

public enum CarBrand {

    TOYOTA("TOYOTA"),
    FIAT("FIAT"),
    NISSAN("NISSAN"),
    OPEL("OPEL"),
    RENAULT("RENAULT"),
    KIA("KIA"),
    HYUNDAI("HYUNDAI"),
    MITSHUBISHI("MITSUBISHI"),
    DEFAULT("Brak informacji");

    private String value;

    CarBrand(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
