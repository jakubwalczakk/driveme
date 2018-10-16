package pl.jakub.walczak.driveme.repos.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakub.walczak.driveme.model.address.Address;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByCityAndZipCodeAndStreetAndHouseNo(String city, String zipCode, String street, String houseNo);
}
