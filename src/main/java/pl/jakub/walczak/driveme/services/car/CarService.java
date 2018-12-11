package pl.jakub.walczak.driveme.services.car;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.car.CarBasicDTO;
import pl.jakub.walczak.driveme.dto.car.CarDTO;
import pl.jakub.walczak.driveme.enums.CarBrand;
import pl.jakub.walczak.driveme.mappers.car.CarMapper;
import pl.jakub.walczak.driveme.model.car.Car;
import pl.jakub.walczak.driveme.repos.car.CarRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CarService {

    private CarRepository carRepository;
    private CarMapper carMapper;

    @Autowired
    public CarService(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    // -- methods for controller --
    public Car addCar(CarDTO carDTO) {
        log.info("Adding new Car...");
        Car car = mapDTOToModel(carDTO, Car.builder().build());
        car.setActive(true);
        return carRepository.save(car);
    }

    public void deleteCar(Long id) {
        log.info("Deleting the Car with id = " + id);
        Optional<Car> carToDelete = carRepository.findById(id);
        if (carToDelete.isPresent()) {
            Car car = carToDelete.get();
            car.setActive(false);
            carRepository.save(car);
        } else {
            throw new NoSuchElementException("Cannot DELETE Car with given id = " + id);
        }
    }

    public CarDTO getCar(Long id) {
        log.info("Getting the Car with id = " + id);
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()) {
            return mapModelToDTO(optionalCar.get(), CarDTO.builder().build());
        } else {
            throw new NoSuchElementException("Cannot GET Car with given id = " + id);
        }
    }

    public List<CarDTO> getActiveCars() {
        log.info("Getting all active Cars");
        return findActiveCars().stream().map(car -> mapModelToDTO(car, CarDTO.builder().build()))
                .collect(Collectors.toList());
    }

    public Set<CarBrand> getAllCarBrands() {
        log.info("Getting all Car brands");
        return carRepository.findAllCarBrands();
    }

    public List<CarDTO> getCarsByBrand(String brand) {
        try {
            CarBrand carBrand = CarBrand.valueOf(brand.toUpperCase());
            return carRepository.findAllCarByBrand(carBrand).stream()
                    .map(car -> mapModelToDTO(car, CarDTO.builder().build()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<CarDTO> getAll() {
        log.info("Getting all Cars");
        return findAll().stream().map(car -> mapModelToDTO(car, CarDTO.builder().build()))
                .collect(Collectors.toList());
    }

    // -- dao methods --
    public Optional<Car> findById(Long id) {
        return carRepository.findById(id);
    }

    private Set<Car> findActiveCars() {
        return carRepository.findAllByActive(true);
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    // -- mapper methods --
    public CarDTO mapModelToDTO(Car model, CarDTO dto) {
        return carMapper.mapModelToDTO(model, dto);
    }

    public CarBasicDTO mapModelToBasicDTO(Car model, CarBasicDTO dto) {
        return carMapper.mapModelToBasicDTO(model,dto);
    }

    public Car mapDTOToModel(CarDTO dto, Car model) {
        if (dto.getId() != null) {
            Optional<Car> optionalCar = carRepository.findById(dto.getId());
            if (optionalCar.isPresent()) {
                model = optionalCar.get();
            }
        }
        model = carMapper.mapDTOToModel(dto, model);
        return carRepository.save(model);
    }
}
