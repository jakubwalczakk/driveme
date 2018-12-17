package pl.jakub.walczak.driveme.mappers.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.car.CarBasicDTO;
import pl.jakub.walczak.driveme.dto.car.CarDTO;
import pl.jakub.walczak.driveme.dto.event.CalendarEventDTO;
import pl.jakub.walczak.driveme.dto.user.UserBasicDTO;
import pl.jakub.walczak.driveme.model.car.Car;
import pl.jakub.walczak.driveme.model.city.DrivingCity;
import pl.jakub.walczak.driveme.model.event.CalendarEvent;
import pl.jakub.walczak.driveme.model.user.Instructor;
import pl.jakub.walczak.driveme.model.user.Student;
import pl.jakub.walczak.driveme.model.user.User;
import pl.jakub.walczak.driveme.services.car.CarService;
import pl.jakub.walczak.driveme.services.city.CityService;
import pl.jakub.walczak.driveme.services.user.UserService;
import pl.jakub.walczak.driveme.utils.DateFormatter;

import java.util.Optional;

@Component
public class CalendarEventMapper {

    private UserService userService;
    private CityService cityService;
    private CarService carService;

    @Autowired
    public CalendarEventMapper(UserService userService, CityService cityService, CarService carService) {
        this.userService = userService;
        this.cityService = cityService;
        this.carService = carService;
    }

    public CalendarEventDTO mapModelToDTO(CalendarEvent model, CalendarEventDTO dto) {
        dto.setId(model.getId());
        dto.setStartDate(DateFormatter.formatDateToString(model.getStartDate()));
        dto.setFinishDate(DateFormatter.formatDateToString(model.getFinishDate()));
        dto.setCar(carService.mapModelToBasicDTO(model.getCar(), CarBasicDTO.builder().build()));
        dto.setDrivingCity(model.getDrivingCity().getName());
        dto.setStudent(userService.mapUserBasicModelToDTO(model.getStudent(), UserBasicDTO.builder().build()));
        dto.setInstructor(userService.mapUserBasicModelToDTO(model.getInstructor(), UserBasicDTO.builder().build()));
        return dto;
    }

    public CalendarEvent mapDTOToModel(CalendarEventDTO dto, CalendarEvent model) {
        model.setId(dto.getId());
        model.setStartDate(DateFormatter.parseStringToInstant(dto.getStartDate()));
        model.setFinishDate(DateFormatter.parseStringToInstant(dto.getFinishDate()));

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
        User student = userService.mapUserBasicDTOToModel(dto.getStudent());
        if (student instanceof Student) {
            model.setStudent((Student) student);
        }
        User instructor = userService.mapUserBasicDTOToModel(dto.getInstructor());
        if (instructor instanceof Instructor) {
            model.setInstructor((Instructor) instructor);
        }
        return model;
    }
}
