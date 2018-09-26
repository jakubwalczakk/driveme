package pl.jakub.walczak.driveme.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.enums.CarBrand;
import pl.jakub.walczak.driveme.enums.GasType;
import pl.jakub.walczak.driveme.model.car.Car;
import pl.jakub.walczak.driveme.model.city.DrivingCity;
import pl.jakub.walczak.driveme.repos.car.CarRepository;
import pl.jakub.walczak.driveme.repos.city.DrivingCityRepository;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class DBInitialization {

    @Autowired
    private LicensePlateGenerator licensePlateGenerator;

    private DrivingCityRepository drivingCityRepository;
    private CarRepository carRepository;

    private Set<DrivingCity> drivingCities;
    private Set<Car> cars;

    @Autowired
    public DBInitialization(DrivingCityRepository drivingCityRepository, CarRepository carRepository) {
        this.drivingCityRepository = drivingCityRepository;
        this.carRepository = carRepository;
        this.drivingCities = new HashSet<>();
        this.cars = new HashSet<>();
    }

    @PostConstruct
    public void initialize() {
        initializeDrivingCities();
        initializeCars();
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
        Car punto = Car.builder().brand(CarBrand.FIAT).model("Grande Punto").gasType(GasType.GAS).licensePlateNumber(licensePlateGenerator.generateLicensePlate()).build();
        cars.add(punto);
        Car micra = Car.builder().brand(CarBrand.NISSAN).model("Micra").gasType(GasType.GAS).licensePlateNumber(licensePlateGenerator.generateLicensePlate()).build();
        cars.add(micra);
        Car colt = Car.builder().brand(CarBrand.MITSHUBISHI).model("Colt").gasType(GasType.GAS).licensePlateNumber(licensePlateGenerator.generateLicensePlate()).build();
        cars.add(colt);
        Car yaris = Car.builder().brand(CarBrand.TOYOTA).model("Yaris").gasType(GasType.GAS).licensePlateNumber(licensePlateGenerator.generateLicensePlate()).build();
        cars.add(yaris);
        Car corsa = Car.builder().brand(CarBrand.OPEL).model("Corsa").gasType(GasType.PETROL).licensePlateNumber(licensePlateGenerator.generateLicensePlate()).build();
        cars.add(corsa);
        Car clio = Car.builder().brand(CarBrand.RENAULT).model("Clio").gasType(GasType.OIL).licensePlateNumber(licensePlateGenerator.generateLicensePlate()).build();
        cars.add(clio);
        Car aveo = Car.builder().brand(CarBrand.CHEVROLET).model("Aveo").gasType(GasType.OIL).licensePlateNumber(licensePlateGenerator.generateLicensePlate()).build();
        cars.add(aveo);
        Car hyundai = Car.builder().brand(CarBrand.HYUNDAI).model("i20").gasType(GasType.PETROL).licensePlateNumber(licensePlateGenerator.generateLicensePlate()).build();
        cars.add(hyundai);
        Car swift = Car.builder().brand(CarBrand.SUZUKI).model("Swift").gasType(GasType.ELECTRIC).licensePlateNumber(licensePlateGenerator.generateLicensePlate()).build();
        cars.add(swift);
        Car rio = Car.builder().brand(CarBrand.KIA).model("Rio").gasType(GasType.HYBRID).licensePlateNumber(licensePlateGenerator.generateLicensePlate()).build();
        cars.add(rio);

        carRepository.saveAll(cars);
    }

    private void initializeStudents() {


    }
}
