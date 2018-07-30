package pl.jakub.walczak.driveme.model.address;

import javax.persistence.*;

@Entity(name = "adresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "zip_code")
    private String zipCode;
    @Column(name = "street", nullable = false)
    private String street;
    @Column(name = "house_number", nullable = false)
    private int houseNumber;
    @Column(name = "local_number")
    private int localNumber;

    public Address() {

    }

    public Address(String city, String zipCode, String street, int houseNumber, int localNumber) {
        this.city = city;
        this.zipCode = zipCode;
        this.street = street;
        this.houseNumber = houseNumber;
        this.localNumber = localNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getLocalNumber() {
        return localNumber;
    }

    public void setLocalNumber(int localNumber) {
        this.localNumber = localNumber;
    }
}
