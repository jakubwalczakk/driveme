package pl.jakub.walczak.driveme.mappers.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.car.CarDTO;
import pl.jakub.walczak.driveme.dto.city.DrivingCityDTO;
import pl.jakub.walczak.driveme.dto.event.CalendarEventDTO;
import pl.jakub.walczak.driveme.dto.user.UserBasicDTO;
import pl.jakub.walczak.driveme.model.event.CalendarEvent;
import pl.jakub.walczak.driveme.model.user.Instructor;
import pl.jakub.walczak.driveme.model.user.Student;
import pl.jakub.walczak.driveme.model.user.User;
import pl.jakub.walczak.driveme.services.car.CarService;
import pl.jakub.walczak.driveme.services.city.CityService;
import pl.jakub.walczak.driveme.services.user.UserService;

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
        dto.setDate(model.getDate());
        dto.setMinutesOfEvent(model.getMinutesOfEvent());
        dto.setCar(carService.mapModelToDTO(model.getCar(), CarDTO.builder().build()));
        dto.setDrivingCity(cityService.mapModelToDTO(model.getDrivingCity(), DrivingCityDTO.builder().build()));
        dto.setStudent(userService.mapUserBasicModelToDTO(model.getStudent(), UserBasicDTO.builder().build()));
        dto.setInstructor(userService.mapUserBasicModelToDTO(model.getInstructor(), UserBasicDTO.builder().build()));
        return dto;
    }

    public CalendarEvent mapDTOToModel(CalendarEventDTO dto, CalendarEvent model) {
        model.setId(dto.getId());
        model.setDate(dto.getDate());
        model.setMinutesOfEvent(dto.getMinutesOfEvent());
        model.setCar(carService.mapDTOToModel(dto.getCar(), model.getCar()));
        model.setDrivingCity(cityService.mapDTOToModel(dto.getDrivingCity(), model.getDrivingCity()));
        User student = userService.mapUserBasicDTOToModel(dto.getStudent());
        if (student instanceof Student) {
            model.setStudent((Student) student);
        }
        User instructor = userService.mapUserBasicDTOToModel(dto.getInstructor());
        if (instructor instanceof User) {
            model.setInstructor((Instructor) instructor);
        }
        return model;
    }
}
