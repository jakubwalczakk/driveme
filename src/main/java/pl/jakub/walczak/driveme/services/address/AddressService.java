package pl.jakub.walczak.driveme.services.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.repos.address.AddressRepository;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;
}
