package pl.jakub.walczak.driveme.mappers.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.car.CarBasicDTO;
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
import pl.jakub.walczak.driveme.utils.DateFormatter;

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
        dto.setStudent(userService.mapUserBasicModelToDTO(model.getStudent(), UserBasicDTO.builder().build()));
        dto.setStartDate(DateFormatter.formatDateToString(model.getStartDate()));
        dto.setDuration(model.getDuration());
        dto.setFinishDate(DateFormatter.formatDateToString(model.getFinishDate()));

        dto.setInstructor(userService.mapUserBasicModelToDTO(model.getInstructor(), UserBasicDTO.builder().build()));
        dto.setCar(carService.mapModelToBasicDTO(model.getCar(), CarBasicDTO.builder().build()));
        dto.setDrivingCity(model.getDrivingCity() == null ? null : model.getDrivingCity().getName());
        dto.setTitle(model.getTitle());
        dto.setComment(model.getComment());
        dto.setRating(model.getRating() == null ? null : model.getRating().getValue());
        return dto;
    }

    public Driving mapDTOToModel(DrivingDTO dto, Driving model) {
        model.setId(dto.getId());
        model.setStartDate(DateFormatter.parseStringToInstant(dto.getStartDate()));
        model.setDuration(dto.getDuration());
        model.setFinishDate(DateFormatter.parseStringToInstant(dto.getFinishDate()));

        User student = userService.mapUserBasicDTOToModel(dto.getStudent());
        if (student instanceof Student) {
            model.setStudent((Student) student);
        }

        User instructor = userService.mapUserBasicDTOToModel(dto.getInstructor());
        if (instructor instanceof Instructor) {
            model.setInstructor((Instructor) instructor);
        }

        CarBasicDTO carDTO = dto.getCar();
        if (carDTO != null) {
            Optional<Car> carOptional = carService.findById(carDTO.getId());
            if (carOptional.isPresent()) {
                model.setCar(carOptional.get());
            }
        }

        Optional<DrivingCity> optionalDrivingCity = cityService.findByName(dto.getDrivingCity());
        if (optionalDrivingCity.isPresent()) {
            model.setDrivingCity(optionalDrivingCity.get());
        }
        model.setTitle(dto.getTitle());
        model.setComment(dto.getComment());
        try {
            model.setRating(Rating.of(dto.getRating()));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            model.setRating(Rating.DEFAULT);
        }
        return model;
    }
}
