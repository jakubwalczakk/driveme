package pl.jakub.walczak.driveme.mappers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.jakub.walczak.driveme.dto.address.AddressDTO;
import pl.jakub.walczak.driveme.dto.car.CarDTO;
import pl.jakub.walczak.driveme.enums.CarBrand;
import pl.jakub.walczak.driveme.enums.GasType;
import pl.jakub.walczak.driveme.mappers.car.CarMapper;
import pl.jakub.walczak.driveme.model.address.Address;
import pl.jakub.walczak.driveme.model.car.Car;
import pl.jakub.walczak.driveme.utils.Generator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CarMapperTest {

    @TestConfiguration
    static class CarRepositoryTestContextConfiguration {
        @Bean
        public Generator generator() {
            return new Generator();
        }
    }

    @Autowired
    private Generator generator;

    @Autowired
    private CarMapper carMapper;

    @Test
    public void carMapModelToDTOTest() {
        final String licensePlate = generator.generateLicensePlate();
        final CarBrand carBrand = CarBrand.FIAT;
        final GasType gasType = GasType.OIL;
        final String model = "Grande Punto";

        Car car = Car.builder().licensePlate(licensePlate).brand(carBrand).model(model).gasType(gasType).build();

        CarDTO carDTO = carMapper.mapModelToDTO(car, CarDTO.builder().build());

        assertThat(CarBrand.of(carDTO.getBrand())).isEqualTo(car.getBrand());
        assertThat(carDTO.getLicensePlate()).isEqualTo(car.getLicensePlate());
        assertThat(carDTO.getModel()).isEqualTo(car.getModel());
        assertThat(GasType.of(carDTO.getGasType())).isEqualTo(car.getGasType());
    }

    @Test
    public void carMapDTOToModelTest() {
        final String licensePlate = generator.generateLicensePlate();
        final String carBrand = "FIAT";
        final String gasType = "Ropa naftowa";
        final String model = "Grande Punto";

        CarDTO carDTO = CarDTO.builder().licensePlate(licensePlate).brand(carBrand).model(model).gasType(gasType).build();

        Car car = carMapper.mapDTOToModel(carDTO, Car.builder().build());

        assertThat(CarBrand.of(carDTO.getBrand())).isEqualTo(car.getBrand());
        assertThat(carDTO.getLicensePlate()).isEqualTo(car.getLicensePlate());
        assertThat(carDTO.getModel()).isEqualTo(car.getModel());
        assertThat(GasType.of(carDTO.getGasType())).isEqualTo(car.getGasType());
    }
}