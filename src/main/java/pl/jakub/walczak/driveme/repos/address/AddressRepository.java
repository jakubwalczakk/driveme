package pl.jakub.walczak.driveme.repos.address;

import org.springframework.data.repository.CrudRepository;
import pl.jakub.walczak.driveme.model.address.Address;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
