package pl.jakub.walczak.driveme.enums;

public enum GasType {
    PETROL("Benzyna"),
    GAS("Gaz"),
    OIL("Ropa naftowa");

    private String value;

    GasType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}