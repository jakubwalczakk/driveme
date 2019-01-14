package pl.jakub.walczak.driveme.mappers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.jakub.walczak.driveme.dto.address.AddressDTO;
import pl.jakub.walczak.driveme.mappers.address.AddressMapper;
import pl.jakub.walczak.driveme.model.address.Address;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AddressMapperTest {

    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void addressMapModelToDTOTest() {
        final String city = "Zabrze";
        final String street = "Gliwicka";
        final String zipCode = "41-800";
        final String houseNo = "219";
        Address address = Address.builder().city(city).street(street).zipCode(zipCode).houseNo(houseNo).build();

        AddressDTO addressDTO = addressMapper.mapModelToDTO(address, AddressDTO.builder().build());

        assertThat(addressDTO.getCity()).isEqualTo(address.getCity());
        assertThat(addressDTO.getStreet()).isEqualTo(address.getStreet());
        assertThat(addressDTO.getZipCode()).isEqualTo(address.getZipCode());
        assertThat(addressDTO.getHouseNo()).isEqualTo(address.getHouseNo());
    }

    @Test
    public void addresMapDTOToModelTest() {
        final String city = "Zabrze";
        final String street = "Gliwicka";
        final String zipCode = "41-800";
        final String houseNo = null;

        AddressDTO addressDTO = AddressDTO.builder().city(city).street(street).zipCode(zipCode).houseNo(houseNo).build();

        Address address = addressMapper.mapDTOToModel(addressDTO, Address.builder().build());

        assertThat(addressDTO.getCity()).isEqualTo(address.getCity());
        assertThat(addressDTO.getStreet()).isEqualTo(address.getStreet());
        assertThat(addressDTO.getZipCode()).isEqualTo(address.getZipCode());
        assertThat(addressDTO.getHouseNo()).isEqualTo(address.getHouseNo());
    }
}
