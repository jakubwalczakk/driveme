package pl.jakub.walczak.driveme.services.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.event.EventDTO;
import pl.jakub.walczak.driveme.dto.event.EventsInfoDTO;
import pl.jakub.walczak.driveme.mappers.event.EventMapper;
import pl.jakub.walczak.driveme.model.car.Car;
import pl.jakub.walczak.driveme.model.event.Event;
import pl.jakub.walczak.driveme.model.user.Instructor;
import pl.jakub.walczak.driveme.repos.event.EventRepository;
import pl.jakub.walczak.driveme.services.car.CarService;
import pl.jakub.walczak.driveme.services.user.InstructorService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EventService {

    private EventRepository eventRepository;
    private EventMapper eventMapper;
    private InstructorService instructorService;
    private CarService carService;

    @Autowired
    public EventService(EventRepository eventRepository, EventMapper eventMapper,
                        InstructorService instructorService, CarService carService) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
        this.instructorService = instructorService;
        this.carService = carService;
    }

    // -- methods for controller --
    public Event addEvent(EventDTO eventDTO) {
        log.info("Adding new Event...");
        Event event = mapDTOToModel(eventDTO, Event.builder().build());
        return eventRepository.save(event);
    }

    public void deleteEvent(Long id) {
        log.info("Deleting the Event with id = " + id);
        Optional<Event> eventToDelete = eventRepository.findById(id);
        if (eventToDelete.isPresent()) {
            eventRepository.delete(eventToDelete.get());
        } else {
            throw new NoSuchElementException("Cannot DELETE Event with given id = " + id);
        }
    }

    public EventDTO getEvent(Long id) {
        log.info("Getting the Event with id = " + id);
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isPresent()) {
            return mapModelToDTO(optionalEvent.get(), EventDTO.builder().build());
        } else {
            throw new NoSuchElementException("Cannot GET Event with given id = " + id);
        }
    }

//    public List<EventDTO> getEventsByInstructorAndCar(EventsInfoDTO request) {
//        log.info("Getting all Events by Instructor where Instructor email = " + request.getInstructorInfo() +
//                " and Cars where Car brand = " + request.getCarBrand());
//        String instructorEmail = request.getInstructorInfo().split(" - ")[1];
//        log.info("Split instructor info, email is = " + instructorEmail);
//        Optional<Instructor> optionalInstructor = instructorService.findByEmail(instructorEmail);
////        Optional<Car> optionalCar = carService.findByLicensePlate(request.getCarLicensePlate());
//
//        Set<Car> cars = carService.findAllCarsByBrand(request.getCarBrand());
//        if (optionalInstructor.isPresent() && cars.size() != 0) {
//            Long instructorId = optionalInstructor.get().getId();
//
//            Set<Long> carsIds = cars.stream().map(car -> car.getId()).collect(Collectors.toSet());
//
//            List<Event> events =
//                    eventRepository.findAllByInstructorIdAndCarIdIn(instructorId, carsIds);
//
//            return events.stream().map(event -> mapModelToDTO(event, EventDTO.builder().build())).collect(Collectors.toList());
//
//        } else {
//            log.warn("Cannot find Instructor with email = " + request.getInstructorInfo() +
//                    " or cannnot find Cars with brand = " + request.getCarBrand());
//            throw new NoSuchElementException();
//        }
//    }

    public List<EventDTO> getAll() {
        log.info("Getting all Events");
        return findAll().stream().map(event -> mapModelToDTO(event, EventDTO.builder().build())).collect(Collectors.toList());
    }

    // -- dao methods --
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    // -- mapper methods --
    public EventDTO mapModelToDTO(Event model, EventDTO dto) {
        return eventMapper.mapModelToDTO(model, dto);
    }

    public Event mapDTOToModel(EventDTO dto, Event model) {
        if (dto.getId() != null) {
            Optional<Event> optionalEvent = eventRepository.findById(dto.getId());
            if (optionalEvent.isPresent()) {
                model = optionalEvent.get();
            }
        }
        model = eventMapper.mapDTOToModel(dto, model);
        return eventRepository.save(model);
    }
}
