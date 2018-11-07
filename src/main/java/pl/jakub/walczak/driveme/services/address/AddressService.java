package pl.jakub.walczak.driveme.services.address;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.address.AddressDTO;
import pl.jakub.walczak.driveme.mappers.address.AddressMapper;
import pl.jakub.walczak.driveme.model.address.Address;
import pl.jakub.walczak.driveme.repos.address.AddressRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
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
        log.info("Adding new Address...");
        Optional<Address> optionalAddress = addressRepository.findByCityAndZipCodeAndStreetAndHouseNo(
                addressDTO.getCity(), addressDTO.getZipCode(), addressDTO.getStreet(), addressDTO.getHouseNo());
        if (!optionalAddress.isPresent()) {
            Address address = mapDTOToModel(addressDTO, Address.builder().build());
            return addressRepository.save(address);
        }
        log.info("Given address just exists in database.");
        return optionalAddress.get();
    }

    public AddressDTO getAddress(Long id) {
        log.info("Getting the Address with id = " + id);
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()) {
            return mapModelToDTO(optionalAddress.get(), AddressDTO.builder().build());
        } else {
            throw new NoSuchElementException("Cannot GET Address with given id = " + id);
        }
    }

    public List<AddressDTO> getAll() {
        log.info("Getting all Addresses");
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
