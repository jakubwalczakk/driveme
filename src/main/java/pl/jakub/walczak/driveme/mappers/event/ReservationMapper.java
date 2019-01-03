package pl.jakub.walczak.driveme.mappers.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.event.ReservationDTO;
import pl.jakub.walczak.driveme.dto.user.UserBasicDTO;
import pl.jakub.walczak.driveme.enums.CarBrand;
import pl.jakub.walczak.driveme.model.car.Car;
import pl.jakub.walczak.driveme.model.city.DrivingCity;
import pl.jakub.walczak.driveme.model.event.Driving;
import pl.jakub.walczak.driveme.model.event.Reservation;
import pl.jakub.walczak.driveme.model.user.Instructor;
import pl.jakub.walczak.driveme.model.user.Student;
import pl.jakub.walczak.driveme.model.user.User;
import pl.jakub.walczak.driveme.services.city.CityService;
import pl.jakub.walczak.driveme.services.user.UserService;
import pl.jakub.walczak.driveme.utils.DateFormatter;

import java.util.Optional;

@Component
public class ReservationMapper {

    private UserService userService;
    private CityService cityService;

    @Autowired
    public ReservationMapper(UserService userService, CityService cityService) {
        this.userService = userService;
        this.cityService = cityService;
    }

    public ReservationDTO mapModelToDTO(Reservation model, ReservationDTO dto) {
        dto.setId(model.getId());
        dto.setStudent(userService.mapUserBasicModelToDTO(model.getStudent(), UserBasicDTO.builder().build()));
        dto.setStartDate(DateFormatter.formatDateToString(model.getStartDate()));
        dto.setDuration(model.getDuration());
        dto.setFinishDate(DateFormatter.formatDateToString(model.getFinishDate()));

        dto.setInstructor(userService.mapUserBasicModelToDTO(model.getInstructor(), UserBasicDTO.builder().build()));
        dto.setCarBrand(model.getCarBrand() == null ? null : model.getCarBrand().getValue());
        dto.setDrivingCity(model.getDrivingCity() == null ? null : model.getDrivingCity().getName());
        dto.setAccepted(model.getAccepted());
        dto.setEventType(model.getEventType().getValue());
        return dto;
    }

    public Reservation mapDTOToModel(ReservationDTO dto, Reservation model) {
        model.setId(dto.getId());
        model.setStartDate(dto.getStartDate() == null ? model.getStartDate() : DateFormatter.parseStringToInstant(dto.getStartDate()));
        model.setDuration(dto.getDuration() == null ? model.getDuration() : dto.getDuration());
        model.setFinishDate(dto.getFinishDate() == null ? model.getFinishDate() : DateFormatter.parseStringToInstant(dto.getFinishDate()));

        User student = userService.mapUserBasicDTOToModel(dto.getStudent());
        if (student instanceof Student) {
            model.setStudent((Student) student);
        }

        try {
            model.setCarBrand(dto.getCarBrand() == null ? model.getCarBrand() : CarBrand.of((dto.getCarBrand())));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            model.setCarBrand(CarBrand.DEFAULT);
        }

        Optional<DrivingCity> optionalDrivingCity = cityService.findByName(dto.getDrivingCity());
        if (optionalDrivingCity.isPresent()) {
            model.setDrivingCity(optionalDrivingCity.get());
        }

        User instructor = userService.mapUserBasicDTOToModel(dto.getInstructor());
        if (instructor instanceof Instructor) {
            model.setInstructor((Instructor) instructor);
        }
        model.setAccepted(dto.getAccepted() == null ? model.getAccepted() : dto.getAccepted());
        return model;
    }

    public Driving mapReservationIntoDriving(Reservation reservation, Car car) {
        Driving driving = Driving.builder()
                .student(reservation.getStudent())
                .startDate(reservation.getStartDate())
                .duration(reservation.getDuration())
                .finishDate(reservation.getFinishDate())
                .instructor(reservation.getInstructor())
                .car(car)
                .drivingCity(reservation.getDrivingCity())
                .build();
        return driving;
    }
}
