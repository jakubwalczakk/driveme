package pl.jakub.walczak.driveme.repos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.jakub.walczak.driveme.enums.CarBrand;
import pl.jakub.walczak.driveme.enums.GasType;
import pl.jakub.walczak.driveme.model.car.Car;
import pl.jakub.walczak.driveme.repos.car.CarRepository;
import pl.jakub.walczak.driveme.utils.Generator;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
@DataJpaTest
@TestPropertySource(
        locations = "classpath:application-test.properties")
public class CarRepositoryTest {

    @TestConfiguration
    static class CarRepositoryTestContextConfiguration {
        @Bean
        public Generator generator() {
            return new Generator();
        }
    }

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private Generator generator;

    @Test
    public void whenFindCarsByBrandTheReturnCars() {
        //given
        Car punto = Car.builder().brand(CarBrand.FIAT).model("Grande Punto").gasType(GasType.GAS)
                .licensePlate(generator.generateLicensePlate()).active(true).build();
        entityManager.persistAndFlush(punto);

        Car yaris = Car.builder().brand(CarBrand.TOYOTA).model("Yaris").gasType(GasType.PETROL)
                .licensePlate(generator.generateLicensePlate()).active(true).build();
        entityManager.persistAndFlush(yaris);

        Car punto2 = Car.builder().brand(CarBrand.FIAT).model("Grande Punto").gasType(GasType.OIL)
                .licensePlate(generator.generateLicensePlate()).active(true).build();
        entityManager.persistAndFlush(punto2);

        //when
        final List<Car> allCarByBrand = carRepository.findAllCarByBrand(CarBrand.FIAT);

        //then
        assertThat(allCarByBrand.size()).isEqualTo(2);
        assertThat(allCarByBrand.contains(punto)).isEqualTo(true);
        assertThat(allCarByBrand.contains(punto2)).isEqualTo(true);
        assertThat(allCarByBrand.contains(yaris)).isEqualTo(false);

    }

    @Test
    public void whenFindAllCarBrandsThenReturnCarBrands() {
        //given
        Car punto = Car.builder().brand(CarBrand.FIAT).model("Grande Punto").gasType(GasType.GAS)
                .licensePlate(generator.generateLicensePlate()).active(true).build();
        entityManager.persistAndFlush(punto);

        Car yaris = Car.builder().brand(CarBrand.TOYOTA).model("Yaris").gasType(GasType.PETROL)
                .licensePlate(generator.generateLicensePlate()).active(true).build();
        entityManager.persistAndFlush(yaris);

        Car rio = Car.builder().brand(CarBrand.KIA).model("Rio").gasType(GasType.PETROL)
                .licensePlate(generator.generateLicensePlate()).active(true).build();
        entityManager.persistAndFlush(rio);

        //when
        final Set<CarBrand> allCarBrands = carRepository.findAllCarBrands();

        //then
        assertThat(allCarBrands.size()).isEqualTo(3);
        assertThat(allCarBrands.contains(CarBrand.FIAT)).isEqualTo(true);
        assertThat(allCarBrands.contains(CarBrand.TOYOTA)).isEqualTo(true);
        assertThat(allCarBrands.contains(CarBrand.KIA)).isEqualTo(true);
        assertThat(allCarBrands.contains(CarBrand.MITSUBISHI)).isEqualTo(false);
    }

    @Test
    public void whenFindCarByLicensePlateThenReturnCar() {
        // given
        final String licensePlate = generator.generateLicensePlate();
        Car punto = Car.builder().brand(CarBrand.FIAT).model("Grande Punto").gasType(GasType.GAS)
                .licensePlate(licensePlate).active(true).build();
        entityManager.persistAndFlush(punto);

        // when
        final Car carFromDB =
                carRepository.findByLicensePlate(licensePlate).orElse(null);

        // then
        assertThat(carFromDB)
                .isEqualTo(punto);
    }

}
