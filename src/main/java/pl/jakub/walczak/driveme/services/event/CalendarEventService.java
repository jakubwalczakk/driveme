package pl.jakub.walczak.driveme.services.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.event.CalendarEventDTO;
import pl.jakub.walczak.driveme.dto.event.CalendarEventsInfoDTO;
import pl.jakub.walczak.driveme.mappers.event.CalendarEventMapper;
import pl.jakub.walczak.driveme.model.car.Car;
import pl.jakub.walczak.driveme.model.event.CalendarEvent;
import pl.jakub.walczak.driveme.model.user.Instructor;
import pl.jakub.walczak.driveme.repos.event.CalendarEventRepository;
import pl.jakub.walczak.driveme.services.car.CarService;
import pl.jakub.walczak.driveme.services.user.InstructorService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CalendarEventService {

    private CalendarEventRepository calendarEventRepository;
    private CalendarEventMapper calendarEventMapper;
    private InstructorService instructorService;
    private CarService carService;

    @Autowired
    public CalendarEventService(CalendarEventRepository calendarEventRepository, CalendarEventMapper calendarEventMapper,
                                InstructorService instructorService, CarService carService) {
        this.calendarEventRepository = calendarEventRepository;
        this.calendarEventMapper = calendarEventMapper;
        this.instructorService = instructorService;
        this.carService = carService;
    }

    // -- methods for controller --
    public CalendarEvent addCalendarEvent(CalendarEventDTO calendarEventDTO) {
        log.info("Adding new CalendarEvent...");
        CalendarEvent calendarEvent = mapDTOToModel(calendarEventDTO, CalendarEvent.builder().build());
        return calendarEventRepository.save(calendarEvent);
    }

    public void deleteCalendarEvent(Long id) {
        log.info("Deleting the CalendarEvent with id = " + id);
        Optional<CalendarEvent> calendarEventToDelete = calendarEventRepository.findById(id);
        if (calendarEventToDelete.isPresent()) {
            calendarEventRepository.delete(calendarEventToDelete.get());
        } else {
            throw new NoSuchElementException("Cannot DELETE CalendarEvent with given id = " + id);
        }
    }

    public CalendarEventDTO getCalendarEvent(Long id) {
        log.info("Getting the CalendarEvent with id = " + id);
        Optional<CalendarEvent> optionalCalendarEvent = calendarEventRepository.findById(id);
        if (optionalCalendarEvent.isPresent()) {
            return mapModelToDTO(optionalCalendarEvent.get(), CalendarEventDTO.builder().build());
        } else {
            throw new NoSuchElementException("Cannot GET CalendarEvent with given id = " + id);
        }
    }

    public List<CalendarEventDTO> getSpecifiedCalendarEvents(CalendarEventsInfoDTO request) {
        log.info("Getting all CalendarEvents by Instructor where Instructor email = " + request.getInstructorInfo() +
                " and Cars where Car brand = " + request.getCarBrand());
        String instructorEmail = request.getInstructorInfo().split(" - ")[1];
        log.info("Split instructor info, email is = " + instructorEmail);
        Optional<Instructor> optionalInstructor = instructorService.findByEmail(instructorEmail);
//        Optional<Car> optionalCar = carService.findByLicensePlate(request.getCarLicensePlate());

        Set<Car> cars = carService.findAllCarsByBrand(request.getCarBrand());
        if (optionalInstructor.isPresent() && cars.size() != 0) {
            Long instructorId = optionalInstructor.get().getId();

            Set<Long> carsIds = cars.stream().map(car -> car.getId()).collect(Collectors.toSet());

            List<CalendarEvent> calendarEvents =
                    calendarEventRepository.findAllByInstructorIdAndCarIdIn(instructorId, carsIds);

            return calendarEvents.stream().map(event -> mapModelToDTO(event, CalendarEventDTO.builder().build())).collect(Collectors.toList());

        } else {
            log.warn("Cannot find Instructor with email = " + request.getInstructorInfo() +
                    " or cannnot find Cars with brand = " + request.getCarBrand());
            throw new NoSuchElementException();
        }
    }

    public List<CalendarEventDTO> getAll() {
        log.info("Getting all CalendarEvents");
        return findAll().stream().map(event -> mapModelToDTO(event, CalendarEventDTO.builder().build())).collect(Collectors.toList());
    }

    // -- dao methods --
    public List<CalendarEvent> findAll() {
        return calendarEventRepository.findAll();
    }

    // -- mapper methods --
    public CalendarEventDTO mapModelToDTO(CalendarEvent model, CalendarEventDTO dto) {
        return calendarEventMapper.mapModelToDTO(model, dto);
    }

    public CalendarEvent mapDTOToModel(CalendarEventDTO dto, CalendarEvent model) {
        if (dto.getId() != null) {
            Optional<CalendarEvent> optionalCalendarEvent = calendarEventRepository.findById(dto.getId());
            if (optionalCalendarEvent.isPresent()) {
                model = optionalCalendarEvent.get();
            }
        }
        model = calendarEventMapper.mapDTOToModel(dto, model);
        return calendarEventRepository.save(model);
    }
}
