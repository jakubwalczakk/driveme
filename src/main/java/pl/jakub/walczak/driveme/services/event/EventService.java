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
import pl.jakub.walczak.driveme.model.event.Driving;
import pl.jakub.walczak.driveme.model.event.Event;
import pl.jakub.walczak.driveme.model.event.Reservation;
import pl.jakub.walczak.driveme.model.event.exam.PracticalExam;
import pl.jakub.walczak.driveme.repos.event.DrivingRepository;
import pl.jakub.walczak.driveme.repos.event.EventRepository;
import pl.jakub.walczak.driveme.repos.event.ReservationRepository;
import pl.jakub.walczak.driveme.repos.event.exam.PracticalExamRepository;
import pl.jakub.walczak.driveme.services.car.CarService;
import pl.jakub.walczak.driveme.services.event.exam.PracticalExamService;
import pl.jakub.walczak.driveme.services.user.InstructorService;

import java.time.Instant;
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
        eventRepository.delete(eventToDelete.orElseThrow(() ->
                new NoSuchElementException("Cannot DELETE Event with given id = " + id)));
    }

    public EventDTO getEvent(Long id) {
        log.info("Getting the Event with id = " + id);
        Optional<Event> optionalEvent = eventRepository.findById(id);
        return mapModelToDTO(optionalEvent.orElseThrow(() ->
                new NoSuchElementException("Cannot GET Event with given id = " + id)), EventDTO.builder().build());
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


    public Boolean checkAvailabilityOfTerm(String instructorEmail, String brand, String date, Integer duration) {
        log.info("INSTRUCTOR = " + instructorEmail);
        log.info("CAR BRAND = " + brand);
        log.info("START DATE = " + date);
        log.info("DURATION = " + duration);

        log.info("Checking the availability of term " + date);

        CarBrand carBrand = CarBrand.of(brand.trim());

        Instant startDate = Instant.parse(date);
        Instant finishDate = startDate.plusSeconds(duration * 60);

        List<Driving> drivings =
                drivingRepository.findAllByInstructorEmailAndCar_Brand(instructorEmail, carBrand);

        List<Reservation> reservations =
                reservationRepository.findAllByInstructorEmailAndCarBrand(instructorEmail, carBrand);

        List<PracticalExam> practicalExams =
                practicalExamRepository.findAllByInstructorEmailAndCar_Brand(instructorEmail, carBrand);

        for (Driving d : drivings) {
            if (d.getStartDate().isAfter(startDate) && d.getFinishDate().isBefore(finishDate) ||
                    d.getStartDate().isBefore(startDate) && d.getFinishDate().isAfter(startDate) ||
                    d.getStartDate().isBefore(finishDate) && d.getFinishDate().isAfter(finishDate)) {
                log.info("Reservation in term " + date + " is NOT available.");
                return false;
            }
        }

        for (Reservation r : reservations) {
            if (r.getStartDate().isAfter(startDate) && r.getFinishDate().isBefore(finishDate) ||
                    r.getStartDate().isBefore(startDate) && r.getFinishDate().isAfter(startDate) ||
                    r.getStartDate().isBefore(finishDate) && r.getFinishDate().isAfter(finishDate)) {
                log.info("Reservation in term " + date + " is NOT available.");
                return false;
            }
        }

        for (PracticalExam pE : practicalExams) {
            if (pE.getStartDate().isAfter(startDate) && pE.getFinishDate().isBefore(finishDate) ||
                    pE.getStartDate().isBefore(startDate) && pE.getFinishDate().isAfter(startDate) ||
                    pE.getStartDate().isBefore(finishDate) && pE.getFinishDate().isAfter(finishDate)) {
                log.info("Reservation in term " + date + " is NOT available.");
                return false;
            }
        }

        log.info("Reservation in term " + date + " is available.");
        return true;
    }


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
            model = optionalEvent.orElse(model);
        }
        model = eventMapper.mapDTOToModel(dto, model);
        return eventRepository.save(model);
    }
}
