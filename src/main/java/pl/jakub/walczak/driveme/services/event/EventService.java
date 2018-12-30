package pl.jakub.walczak.driveme.services.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.calendar.CalendarEventsDTO;
import pl.jakub.walczak.driveme.dto.event.DrivingDTO;
import pl.jakub.walczak.driveme.dto.event.EventDTO;
import pl.jakub.walczak.driveme.dto.event.ReservationDTO;
import pl.jakub.walczak.driveme.dto.event.exam.PracticalExamDTO;
import pl.jakub.walczak.driveme.enums.CarBrand;
import pl.jakub.walczak.driveme.mappers.event.EventMapper;
import pl.jakub.walczak.driveme.model.event.Event;
import pl.jakub.walczak.driveme.repos.event.DrivingRepository;
import pl.jakub.walczak.driveme.repos.event.EventRepository;
import pl.jakub.walczak.driveme.repos.event.ReservationRepository;
import pl.jakub.walczak.driveme.repos.event.exam.PracticalExamRepository;
import pl.jakub.walczak.driveme.services.car.CarService;
import pl.jakub.walczak.driveme.services.event.exam.PracticalExamService;
import pl.jakub.walczak.driveme.services.user.InstructorService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EventService {

    private EventRepository eventRepository;
    private EventMapper eventMapper;

    private DrivingRepository drivingRepository;
    private DrivingService drivingService;

    private ReservationRepository reservationRepository;
    private ReservationService reservationService;

    private PracticalExamRepository practicalExamRepository;
    private PracticalExamService practicalExamService;

    private InstructorService instructorService;
    private CarService carService;

    @Autowired
    public EventService(EventRepository eventRepository, EventMapper eventMapper,
                        DrivingRepository drivingRepository, DrivingService drivingService,
                        ReservationRepository reservationRepository, ReservationService reservationService,
                        PracticalExamRepository practicalExamRepository, PracticalExamService practicalExamService,
                        InstructorService instructorService, CarService carService) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
        this.drivingRepository = drivingRepository;
        this.drivingService = drivingService;
        this.reservationRepository = reservationRepository;
        this.reservationService = reservationService;
        this.practicalExamRepository = practicalExamRepository;
        this.practicalExamService = practicalExamService;
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

    //FIXME??
    public CalendarEventsDTO getAllSpecifiedEvents(String instructorEmail, String carBrand) {
        log.info("Getting all Events specified by Instructor = " + instructorEmail + " and brand = " + carBrand);
        try {
            CarBrand brand = CarBrand.of(carBrand);

            List<DrivingDTO> drivingDTOS =
                    drivingRepository.findAllByInstructorEmailAndCar_Brand(instructorEmail, brand)
                            .stream()
                            .map(driving -> drivingService.mapModelToDTO(driving, DrivingDTO.builder().build()))
                            .collect(Collectors.toList());

            List<ReservationDTO> reservationDTOS =
                    reservationRepository.findAllByInstructorEmailAndCarBrand(instructorEmail, brand)
                            .stream()
                            .map(reservation -> reservationService.mapModelToDTO(reservation, ReservationDTO.builder().build()))
                            .collect(Collectors.toList());

            List<PracticalExamDTO> practicalExamDTOS =
                    practicalExamRepository.findAllByInstructorEmailAndCar_Brand(instructorEmail, brand)
                            .stream()
                            .map(practicalExam -> practicalExamService.mapModelToDTO(practicalExam, PracticalExamDTO.builder().build()))
                            .collect(Collectors.toList());

            return CalendarEventsDTO.builder().drivings(drivingDTOS).reservations(reservationDTOS).exams(practicalExamDTOS).build();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
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
