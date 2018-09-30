package pl.jakub.walczak.driveme.model.address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private String houseNumber;
}
