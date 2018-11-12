package pl.jakub.walczak.driveme.mappers.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.car.CarDTO;
import pl.jakub.walczak.driveme.dto.event.DrivingDTO;
import pl.jakub.walczak.driveme.dto.user.UserBasicDTO;
import pl.jakub.walczak.driveme.enums.Rating;
import pl.jakub.walczak.driveme.model.car.Car;
import pl.jakub.walczak.driveme.model.city.DrivingCity;
import pl.jakub.walczak.driveme.model.event.Driving;
import pl.jakub.walczak.driveme.model.user.Instructor;
import pl.jakub.walczak.driveme.model.user.Student;
import pl.jakub.walczak.driveme.model.user.User;
import pl.jakub.walczak.driveme.services.car.CarService;
import pl.jakub.walczak.driveme.services.city.CityService;
import pl.jakub.walczak.driveme.services.user.UserService;

import java.util.Optional;

@Component
public class DrivingMapper {

    private UserService userService;
    private CityService cityService;
    private CarService carService;

    @Autowired
    public DrivingMapper(UserService userService, CityService cityService, CarService carService) {
        this.userService = userService;
        this.cityService = cityService;
        this.carService = carService;
    }

    public DrivingDTO mapModelToDTO(Driving model, DrivingDTO dto) {
        dto.setId(model.getId());
        dto.setDate(model.getDate());
        dto.setMinutesOfEvent(model.getDuration());
        dto.setCar(carService.mapModelToDTO(model.getCar(), CarDTO.builder().build()));
        dto.setDrivingCity(model.getDrivingCity().getName());
        dto.setStudent(userService.mapUserBasicModelToDTO(model.getStudent(), UserBasicDTO.builder().build()));
        dto.setInstructor(userService.mapUserBasicModelToDTO(model.getInstructor(), UserBasicDTO.builder().build()));
        dto.setTitle(model.getTitle());
        dto.setRating(model.getRating().toString());
        return dto;
    }

    public Driving mapDTOToModel(DrivingDTO dto, Driving model) {
        model.setId(dto.getId());
        model.setDate(dto.getDate());
        model.setDuration(dto.getMinutesOfEvent());

        CarDTO carDTO = dto.getCar();
        if(carDTO!=null){
            Optional<Car> carOptional = carService.findById(carDTO.getId());
            if(carOptional.isPresent()){
                model.setCar(carOptional.get());
            }
        }

        Optional<DrivingCity> optionalDrivingCity = cityService.findByName(dto.getDrivingCity());
        if (optionalDrivingCity.isPresent()) {
            model.setDrivingCity(optionalDrivingCity.get());
        }
        User student = userService.mapUserBasicDTOToModel(dto.getStudent());
        if (student instanceof Student) {
            model.setStudent((Student) student);
        }
        User instructor = userService.mapUserBasicDTOToModel(dto.getInstructor());
        if (instructor instanceof Instructor) {
            model.setInstructor((Instructor) instructor);
        }
        model.setTitle(dto.getTitle());
        try {
            model.setRating(Rating.valueOf(dto.getRating()));
        } catch (IllegalArgumentException | NullPointerException e) {
            e.printStackTrace();
            model.setRating(Rating.DEFAULT);
        }
        return model;
    }
}
