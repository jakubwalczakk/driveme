package pl.jakub.walczak.driveme.services.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.calendar.CalendarEventsDTO;
import pl.jakub.walczak.driveme.dto.event.DrivingDTO;
import pl.jakub.walczak.driveme.dto.event.EventDTO;
import pl.jakub.walczak.driveme.dto.event.exam.PracticalExamDTO;
import pl.jakub.walczak.driveme.enums.CarBrand;
import pl.jakub.walczak.driveme.mappers.event.EventMapper;
import pl.jakub.walczak.driveme.model.event.Driving;
import pl.jakub.walczak.driveme.model.event.Event;
import pl.jakub.walczak.driveme.model.event.PracticalExam;
import pl.jakub.walczak.driveme.repos.event.DrivingRepository;
import pl.jakub.walczak.driveme.repos.event.EventRepository;
import pl.jakub.walczak.driveme.repos.event.PracticalExamRepository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
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

    private PracticalExamRepository practicalExamRepository;
    private PracticalExamService practicalExamService;


    @Autowired
    public EventService(EventRepository eventRepository, EventMapper eventMapper,
                        DrivingRepository drivingRepository, DrivingService drivingService,
                        PracticalExamRepository practicalExamRepository, PracticalExamService practicalExamService) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
        this.drivingRepository = drivingRepository;
        this.drivingService = drivingService;
        this.practicalExamRepository = practicalExamRepository;
        this.practicalExamService = practicalExamService;
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

    public CalendarEventsDTO getAllSpecifiedEvents(Long instructorId, String carBrand) {
        try {
            Instant startDate = Instant.now().plus(3, ChronoUnit.DAYS).truncatedTo(ChronoUnit.DAYS);
            log.info("Getting all Events specified by Instructor ID = " + instructorId + " and Car brand = " + carBrand
                    + " and StartDate after " + startDate);
            if (carBrand == null) {
                List<DrivingDTO> drivingDTOS = convertListOfDrivingsModelsToDTO(
                        drivingRepository.findAllByInstructorIdAndStartDateAfterOrderByStartDateDesc(
                                instructorId, startDate));

                List<PracticalExamDTO> practicalExamDTOS = convertListOfPracticalExamsModelsToDTO(
                        practicalExamRepository.findAllByInstructorIdAndStartDateAfterOrderByStartDateDesc(
                                instructorId, startDate));

                return CalendarEventsDTO.builder().drivings(drivingDTOS).exams(practicalExamDTOS).build();
            } else {
                CarBrand brand = CarBrand.of(carBrand);

                List<DrivingDTO> drivingDTOS = convertListOfDrivingsModelsToDTO(
                        drivingRepository.findAllByInstructorIdAndCarBrandAndStartDateAfterOrderByStartDateDesc(
                                instructorId, brand, startDate));

                List<PracticalExamDTO> practicalExamDTOS = convertListOfPracticalExamsModelsToDTO(
                        practicalExamRepository.findAllByInstructorIdAndCarBrandAndStartDateAfterOrderByStartDateDesc(
                                instructorId, brand, startDate));

                return CalendarEventsDTO.builder().drivings(drivingDTOS).exams(practicalExamDTOS).build();
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }


    public Boolean checkAvailabilityOfTerm(Long instructorId, String brand, String date, Integer duration) {
        log.info("Checking the availability of term " + date);
        if (instructorId != null &&
                brand != null &&
                date != null &&
                duration != null) {

            CarBrand carBrand = CarBrand.of(brand.trim());
            Instant startDate = Instant.parse(date);
            Instant finishDate = startDate.plus(duration, ChronoUnit.MINUTES);

            if (checkAvailabilityOfInstructor(instructorId, startDate, finishDate) &&
                    checkAvailabilityOfCar(carBrand, startDate, finishDate)) {
                log.info("Reservation of term " + date + " is available.");
                return true;
            }
        }
        log.info("Reservation of term " + date + " is NOT available.");
        return false;
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

    public Boolean checkAvailabilityOfInstructor(Long instructorId, Instant startDate, Instant finishDate) {

        List<PracticalExam> practicalExams = practicalExamRepository.findAllByInstructorId(instructorId);
        List<Driving> drivings = drivingRepository.findAllByInstructorId(instructorId);

        if (checkDateAvailabilityOfPracticalExams(practicalExams, startDate, finishDate) &&
                checkDateAvailabilityOfDrivings(drivings, startDate, finishDate)) {
            return true;
        }
        return false;
    }

    public Boolean checkAvailabilityOfCar(CarBrand carBrand, Instant startDate, Instant finishDate) {

        List<PracticalExam> practicalExams = practicalExamRepository.findAllByCarBrand(carBrand);
        List<Driving> drivings = drivingRepository.findAllByCarBrand(carBrand);

        if (checkDateAvailabilityOfPracticalExams(practicalExams, startDate, finishDate) &&
                checkDateAvailabilityOfDrivings(drivings, startDate, finishDate)) {
            return true;
        }
        return true;
    }

    private boolean checkDateAvailabilityOfDrivings(List<Driving> drivings, Instant startDate, Instant finishDate) {
        for (Driving driving : drivings) {
            if (driving.getStartDate().isAfter(startDate) && driving.getFinishDate().isBefore(finishDate) ||
                    driving.getStartDate().isBefore(startDate) && driving.getFinishDate().isAfter(startDate) ||
                    driving.getStartDate().isBefore(finishDate) && driving.getFinishDate().isAfter(finishDate)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkDateAvailabilityOfPracticalExams(List<PracticalExam> practicalExams, Instant startDate, Instant finishDate) {
        for (PracticalExam practicalExam : practicalExams) {
            if (practicalExam.getStartDate().isAfter(startDate) && practicalExam.getFinishDate().isBefore(finishDate) ||
                    practicalExam.getStartDate().isBefore(startDate) && practicalExam.getFinishDate().isAfter(startDate) ||
                    practicalExam.getStartDate().isBefore(finishDate) && practicalExam.getFinishDate().isAfter(finishDate)) {
                return false;
            }
        }
        return true;
    }

    private List<DrivingDTO> convertListOfDrivingsModelsToDTO(List<Driving> drivings) {
        return drivings.stream()
                .map(driving -> drivingService.mapModelToDTO(driving, DrivingDTO.builder().build()))
                .collect(Collectors.toList());
    }

    private List<PracticalExamDTO> convertListOfPracticalExamsModelsToDTO(List<PracticalExam> practicalExams) {
        return practicalExams.stream()
                .map(practicalExam -> practicalExamService.mapModelToDTO(practicalExam, PracticalExamDTO.builder().build()))
                .collect(Collectors.toList());
    }
}
