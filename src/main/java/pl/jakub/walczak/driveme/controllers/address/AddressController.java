package pl.jakub.walczak.driveme.controllers.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.jakub.walczak.driveme.services.address.AddressService;

@RestController
@RequestMapping(path = "/address")
public class AddressController {

    @Autowired
    private AddressService addressService;
}
