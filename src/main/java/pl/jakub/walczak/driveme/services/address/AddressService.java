package pl.jakub.walczak.driveme.services.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.address.AddressDTO;
import pl.jakub.walczak.driveme.mappers.address.AddressMapper;
import pl.jakub.walczak.driveme.model.address.Address;
import pl.jakub.walczak.driveme.repos.address.AddressRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressService {

    private AddressRepository addressRepository;
    private AddressMapper addressMapper;

    @Autowired
    public AddressService(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    // -- methods for controller --
    public Address addAddress(AddressDTO addressDTO) {
        Address address = mapDTOToModel(addressDTO, Address.builder().build());
        return addressRepository.save(address);
    }

    public List<AddressDTO> getAll() {
        return findAll().stream().map(address -> mapModelToDTO(address, AddressDTO.builder().build())).collect(Collectors.toList());
    }

    // -- dao methods --
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    // -- mapper methods --
    public AddressDTO mapModelToDTO(Address model, AddressDTO dto) {
        return addressMapper.mapModelToDTO(model, dto);
    }

    public Address mapDTOToModel(AddressDTO dto, Address model) {
        if (dto.getId() != null) {
            Optional<Address> optionalAddress = addressRepository.findById(dto.getId());
            if (optionalAddress.isPresent()) {
                model = optionalAddress.get();
            }
        }
        model = addressMapper.mapDTOToModel(dto, model);
        return addressRepository.save(model);
    }
}
