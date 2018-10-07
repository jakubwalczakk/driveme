package pl.jakub.walczak.driveme.enums;

public enum Rating {

    DISAPPOINTING("DISAPPOINTING"),
    AVERAGE("AVERAGE"),
    OK("OK"),
    GREAT("GREAT"),
    MASTER("MASTER");


    private String value;

    Rating(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
