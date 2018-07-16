package pl.jakub.walczak.driveme.model;

import pl.jakub.walczak.driveme.enums.GasType;

import javax.persistence.*;

@Entity(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "brand", nullable = false)
    private String brand;
    @Column(name = "license_plate_number", nullable = false)
    private String licensePlateNumber;
    @Enumerated
    @JoinColumn(name = "gas_type", nullable = false)
    private GasType gasType;

    public Car(String brand, String licensePlateNumber, GasType gasType) {
        this.brand = brand;
        this.licensePlateNumber = licensePlateNumber;
        this.gasType = gasType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public GasType getGasType() {
        return gasType;
    }

    public void setGasType(GasType gasType) {
        this.gasType = gasType;
    }
}
