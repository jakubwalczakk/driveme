package pl.jakub.walczak.driveme.repos.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakub.walczak.driveme.model.address.Address;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface AddressRepository extends JpaRepository<Address, Long> {
}
