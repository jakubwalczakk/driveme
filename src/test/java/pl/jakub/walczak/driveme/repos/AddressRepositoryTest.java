package pl.jakub.walczak.driveme.repos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.jakub.walczak.driveme.model.address.Address;
import pl.jakub.walczak.driveme.repos.address.AddressRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@TestPropertySource(
        locations = "classpath:application-test.properties")
public class AddressRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void whenFindAddressByAllParamsTheReturnAddress() {

        // given
        final String city = "Zabrze";
        final String street = "Gliwicka";
        final String zipCode = "41-800";
        final String houseNo = "219";
        Address address = Address.builder().city(city).street(street).zipCode(zipCode).houseNo(houseNo).build();
        entityManager.persistAndFlush(address);

        // when
        final Address addressFromDB =
                addressRepository.findByCityAndZipCodeAndStreetAndHouseNo(city, zipCode, street, houseNo).orElse(null);

        // then
        assertThat(addressFromDB)
                .isEqualTo(address);
    }
}
