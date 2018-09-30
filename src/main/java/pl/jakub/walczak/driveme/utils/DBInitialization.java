package pl.jakub.walczak.driveme.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.enums.CarBrand;
import pl.jakub.walczak.driveme.enums.GasType;
import pl.jakub.walczak.driveme.model.address.Address;
import pl.jakub.walczak.driveme.model.car.Car;
import pl.jakub.walczak.driveme.model.city.DrivingCity;
import pl.jakub.walczak.driveme.repos.address.AddressRepository;
import pl.jakub.walczak.driveme.repos.car.CarRepository;
import pl.jakub.walczak.driveme.repos.city.DrivingCityRepository;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class DBInitialization {

    private LicensePlateGenerator licensePlateGenerator;

    private DrivingCityRepository drivingCityRepository;
    private CarRepository carRepository;
    private AddressRepository addressRepository;

    private Set<DrivingCity> drivingCities;
    private Set<Car> cars;
    private Set<Address> addresses;

    @Autowired
    public DBInitialization(LicensePlateGenerator licensePlateGenerator, DrivingCityRepository drivingCityRepository,
                            CarRepository carRepository,
                            AddressRepository addressRepository) {
        this.licensePlateGenerator = licensePlateGenerator;
        this.drivingCityRepository = drivingCityRepository;
        this.carRepository = carRepository;
        this.addressRepository = addressRepository;

        this.drivingCities = new HashSet<>();
        this.cars = new HashSet<>();
        this.addresses = new HashSet<>();
    }

    @PostConstruct
    public void initialize() {
        initializeDrivingCities();
        initializeCars();
        initializeAddresses();
    }

    private void initializeDrivingCities() {

        DrivingCity katowice = DrivingCity.builder().name("Katowice").build();
        drivingCities.add(katowice);
        DrivingCity czwa = DrivingCity.builder().name("Częstochowa").build();
        drivingCities.add(czwa);
        DrivingCity sosnowiec = DrivingCity.builder().name("Sosnowiec").build();
        drivingCities.add(sosnowiec);
        DrivingCity gliwice = DrivingCity.builder().name("Gliwice").build();
        drivingCities.add(gliwice);
        DrivingCity zabrze = DrivingCity.builder().name("Zabrze").build();
        drivingCities.add(zabrze);
        DrivingCity bielsko = DrivingCity.builder().name("Bielsko-Biała").build();
        drivingCities.add(bielsko);
        DrivingCity bytom = DrivingCity.builder().name("Bytom").build();
        drivingCities.add(bytom);
        DrivingCity ruda = DrivingCity.builder().name("Ruda Śląska").build();
        drivingCities.add(ruda);
        DrivingCity rybnik = DrivingCity.builder().name("Rybnik").build();
        drivingCities.add(rybnik);
        DrivingCity tychy = DrivingCity.builder().name("Tychy").build();
        drivingCities.add(tychy);

        drivingCityRepository.saveAll(drivingCities);
    }

    private void initializeCars() {
        Car punto =
                Car.builder().brand(CarBrand.FIAT).model("Grande Punto").gasType(GasType.GAS).licensePlate(licensePlateGenerator.generateLicensePlate()).build();
        cars.add(punto);
        Car punto2 =
                Car.builder().brand(CarBrand.FIAT).model("Grande Punto").gasType(GasType.GAS).licensePlate(licensePlateGenerator.generateLicensePlate()).build();
        cars.add(punto2);
        Car punto3 =
                Car.builder().brand(CarBrand.FIAT).model("Grande Punto").gasType(GasType.PETROL).licensePlate(licensePlateGenerator.generateLicensePlate()).build();
        cars.add(punto3);
        Car micra =
                Car.builder().brand(CarBrand.NISSAN).model("Micra").gasType(GasType.GAS).licensePlate(licensePlateGenerator.generateLicensePlate()).build();
        cars.add(micra);
        Car micra2 =
                Car.builder().brand(CarBrand.NISSAN).model("Micra").gasType(GasType.OIL).licensePlate(licensePlateGenerator.generateLicensePlate()).build();
        cars.add(micra2);
        Car colt =
                Car.builder().brand(CarBrand.MITSHUBISHI).model("Colt").gasType(GasType.GAS).licensePlate(licensePlateGenerator.generateLicensePlate()).build();
        cars.add(colt);
        Car colt2 =
                Car.builder().brand(CarBrand.MITSHUBISHI).model("Colt").gasType(GasType.PETROL).licensePlate(licensePlateGenerator.generateLicensePlate()).build();
        cars.add(colt2);
        Car yaris =
                Car.builder().brand(CarBrand.TOYOTA).model("Yaris").gasType(GasType.GAS).licensePlate(licensePlateGenerator.generateLicensePlate()).build();
        cars.add(yaris);
        Car yaris2 =
                Car.builder().brand(CarBrand.TOYOTA).model("Yaris").gasType(GasType.GAS).licensePlate(licensePlateGenerator.generateLicensePlate()).build();
        cars.add(yaris2);
        Car yaris3 =
                Car.builder().brand(CarBrand.TOYOTA).model("Yaris").gasType(GasType.OIL).licensePlate(licensePlateGenerator.generateLicensePlate()).build();
        cars.add(yaris3);
        Car yaris4 =
                Car.builder().brand(CarBrand.TOYOTA).model("Yaris").gasType(GasType.OIL).licensePlate(licensePlateGenerator.generateLicensePlate()).build();
        cars.add(yaris4);
        Car yaris5 =
                Car.builder().brand(CarBrand.TOYOTA).model("Yaris").gasType(GasType.PETROL).licensePlate(licensePlateGenerator.generateLicensePlate()).build();
        cars.add(yaris5);
        Car yaris6 =
                Car.builder().brand(CarBrand.TOYOTA).model("Yaris").gasType(GasType.PETROL).licensePlate(licensePlateGenerator.generateLicensePlate()).build();
        cars.add(yaris6);
        Car corsa =
                Car.builder().brand(CarBrand.OPEL).model("Corsa").gasType(GasType.PETROL).licensePlate(licensePlateGenerator.generateLicensePlate()).build();
        cars.add(corsa);
        Car corsa2 =
                Car.builder().brand(CarBrand.OPEL).model("Corsa").gasType(GasType.GAS).licensePlate(licensePlateGenerator.generateLicensePlate()).build();
        cars.add(corsa2);
        Car clio =
                Car.builder().brand(CarBrand.RENAULT).model("Clio").gasType(GasType.OIL).licensePlate(licensePlateGenerator.generateLicensePlate()).build();
        cars.add(clio);
        Car clio2 =
                Car.builder().brand(CarBrand.RENAULT).model("Clio").gasType(GasType.GAS).licensePlate(licensePlateGenerator.generateLicensePlate()).build();
        cars.add(clio2);
        Car hyundai =
                Car.builder().brand(CarBrand.HYUNDAI).model("i20").gasType(GasType.PETROL).licensePlate(licensePlateGenerator.generateLicensePlate()).build();
        cars.add(hyundai);
        Car hyundai2 =
                Car.builder().brand(CarBrand.HYUNDAI).model("i20").gasType(GasType.OIL).licensePlate(licensePlateGenerator.generateLicensePlate()).build();
        cars.add(hyundai2);
        Car rio =
                Car.builder().brand(CarBrand.KIA).model("Rio").gasType(GasType.OIL).licensePlate(licensePlateGenerator.generateLicensePlate()).build();
        cars.add(rio);
        Car rio2 =
                Car.builder().brand(CarBrand.KIA).model("Rio").gasType(GasType.OIL).licensePlate(licensePlateGenerator.generateLicensePlate()).build();
        cars.add(rio2);
        Car rio3 =
                Car.builder().brand(CarBrand.KIA).model("Rio").gasType(GasType.PETROL).licensePlate(licensePlateGenerator.generateLicensePlate()).build();
        cars.add(rio3);
        Car rio4 =
                Car.builder().brand(CarBrand.KIA).model("Rio").gasType(GasType.PETROL).licensePlate(licensePlateGenerator.generateLicensePlate()).build();
        cars.add(rio4);
        Car rio5 =
                Car.builder().brand(CarBrand.KIA).model("Rio").gasType(GasType.GAS).licensePlate(licensePlateGenerator.generateLicensePlate()).build();
        cars.add(rio5);

        carRepository.saveAll(cars);
    }

    private void initializeAddresses() {

        Address address = Address.builder().city("Gliwice").street("Kujawska").zipCode("44-100").houseNumber("142").build();
        addresses.add(address);

        addressRepository.saveAll(addresses);
    }

    private void initializeStudents() {

    }
}
