package pl.jakub.walczak.driveme.model.gastype;


public enum GasType {
    PETROL("Benzyna"),
    GAS("Gaz"),
    OIL("Ropa naftowa"),
    ELECTRIC("Elektryczny"),
    HYBRID("Hybryda");

    private String value;

    private GasType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
