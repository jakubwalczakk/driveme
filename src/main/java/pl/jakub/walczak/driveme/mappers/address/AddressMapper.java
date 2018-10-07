package pl.jakub.walczak.driveme.mappers.address;

import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.address.AddressDTO;
import pl.jakub.walczak.driveme.model.address.Address;

@Component
public class AddressMapper {

    public AddressDTO mapModelToDTO(Address model, AddressDTO dto) {
        return dto;
    }

    public Address mapDTOToModel(AddressDTO dto, Address model) {
        return model;
    }
}
