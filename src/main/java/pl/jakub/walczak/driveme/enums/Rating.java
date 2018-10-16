package pl.jakub.walczak.driveme.enums;

public enum Rating {

    DISAPPOINTING("Rozczarowująco"),
    AVERAGE("Przeciętnie"),
    OK("W porządku"),
    GREAT("Świetnie"),
    MASTER("Mistrzowsko"),
    DEFAULT("Brak informacji");

    private String value;

    Rating(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
