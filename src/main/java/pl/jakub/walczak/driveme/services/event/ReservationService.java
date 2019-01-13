package pl.jakub.walczak.driveme.services.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.event.ReservationDTO;
import pl.jakub.walczak.driveme.enums.CarBrand;
import pl.jakub.walczak.driveme.mappers.event.ReservationMapper;
import pl.jakub.walczak.driveme.model.car.Car;
import pl.jakub.walczak.driveme.model.city.DrivingCity;
import pl.jakub.walczak.driveme.model.course.Course;
import pl.jakub.walczak.driveme.model.event.Driving;
import pl.jakub.walczak.driveme.model.event.PracticalExam;
import pl.jakub.walczak.driveme.model.event.Reservation;
import pl.jakub.walczak.driveme.model.user.Instructor;
import pl.jakub.walczak.driveme.model.user.Student;
import pl.jakub.walczak.driveme.model.user.User;
import pl.jakub.walczak.driveme.repos.event.DrivingRepository;
import pl.jakub.walczak.driveme.repos.event.PracticalExamRepository;
import pl.jakub.walczak.driveme.repos.event.ReservationRepository;
import pl.jakub.walczak.driveme.services.car.CarService;
import pl.jakub.walczak.driveme.services.city.CityService;
import pl.jakub.walczak.driveme.services.user.InstructorService;
import pl.jakub.walczak.driveme.utils.AuthenticationUtil;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ReservationService {

    private ReservationRepository reservationRepository;
    private ReservationMapper reservationMapper;
    private AuthenticationUtil authenticationUtil;

    private EventService eventService;
    private DrivingRepository drivingRepository;
    private DrivingService drivingService;
    private PracticalExamService practicalExamService;
    private PracticalExamRepository practicalExamRepository;

    private CarService carService;
    private InstructorService instructorService;
    private CityService cityService;

    private final static Integer MINUTE_IN_SECONDS = 60;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, ReservationMapper reservationMapper,
                              AuthenticationUtil authenticationUtil,
                              EventService eventService, DrivingRepository drivingRepository, DrivingService drivingService, PracticalExamService practicalExamService, PracticalExamRepository practicalExamRepository,
                              CarService carService, InstructorService instructorService, CityService cityService) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
        this.authenticationUtil = authenticationUtil;
        this.eventService = eventService;
        this.drivingRepository = drivingRepository;
        this.drivingService = drivingService;
        this.practicalExamService = practicalExamService;
        this.practicalExamRepository = practicalExamRepository;
        this.carService = carService;
        this.instructorService = instructorService;
        this.cityService = cityService;
    }

    // -- methods for controller --
    public Boolean addReservation(ReservationDTO reservationDTO) {
        log.info("Adding new Reservation...");

        if (reservationDTO.getInstructor() != null &&
                reservationDTO.getCarBrand() != null &&
                reservationDTO.getStartDate() != null &&
                reservationDTO.getDuration() != null &&
                reservationDTO.getDrivingCity() != null) {

            if (eventService.checkAvailabilityOfTerm(
                    reservationDTO.getInstructor().getId(),
                    reservationDTO.getCarBrand(), reservationDTO.getStartDate(), reservationDTO.getDuration())) {

                Reservation reservation = createReservationFromDTO(reservationDTO);
                if (reservation != null) {
                    log.info("Reservation is NOT null");
                    reservationRepository.save(reservation);
                    return true;
                }
            }
        }
        throw new NoSuchElementException("Cannot CREATE a new Reservation from given data!");
    }

    public Boolean acceptReservation(Long reservationId) {
        log.info("Accepting Reservation with id = " + reservationId);
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            CarBrand carBrand = reservation.getCarBrand();
            List<Car> cars = carService.findAllCarByBrand(carBrand);
            Student student = reservation.getStudent();
            if (student != null) {
                Course course = student.getCourse();
                if (course != null) {

                    //FIXME
                    List<Car> carsFromDrivingsInternal =
                            extractCarsFromDrivings(drivingRepository
                                    .findAllByCarBrandAndStartDateAfterAndFinishDateBefore(carBrand, reservation.getStartDate(), reservation.getFinishDate()));

                    List<Car> carsFromDrivingsBefore =
                            extractCarsFromDrivings(drivingRepository
                                    .findAllByCarBrandAndStartDateBeforeAndFinishDateAfter(carBrand, reservation.getStartDate(), reservation.getStartDate()));

                    List<Car> carsFromDrivingsAfter =
                            extractCarsFromDrivings(drivingRepository
                                    .findAllByCarBrandAndStartDateBeforeAndFinishDateAfter(carBrand, reservation.getFinishDate(), reservation.getFinishDate()));

                    List<Car> carsFromExamsInternal =
                            extractCarsFromPracticalExams(practicalExamRepository
                                    .findAllByCarBrandAndStartDateAfterAndFinishDateBefore(carBrand, reservation.getStartDate(), reservation.getFinishDate()));

                    List<Car> carsFromExamsBefore =
                            extractCarsFromPracticalExams(practicalExamRepository
                                    .findAllByCarBrandAndStartDateBeforeAndFinishDateAfter(carBrand, reservation.getStartDate(), reservation.getStartDate()));

                    List<Car> carsFromExamsAfter =
                            extractCarsFromPracticalExams(practicalExamRepository
                                    .findAllByCarBrandAndStartDateBeforeAndFinishDateAfter(carBrand, reservation.getFinishDate(), reservation.getFinishDate()));

                    List<Car> carsFromDrivings = new ArrayList<>(carsFromDrivingsInternal);
                    carsFromDrivings.addAll(carsFromDrivingsBefore);
                    carsFromDrivings.addAll(carsFromDrivingsAfter);

                    List<Car> carsFromExams = new ArrayList<>(carsFromExamsInternal);
                    carsFromExams.addAll(carsFromExamsBefore);
                    carsFromExams.addAll(carsFromExamsAfter);

                    List<Car> unavailableCars = new ArrayList<>(carsFromDrivings);
                    unavailableCars.addAll(carsFromExams);

                    List<Car> availableCars = cars.stream()
                            .filter(car -> !unavailableCars.contains(car)).collect(Collectors.toList());

                    if (availableCars.size() != 0) {
                        Car selectedCar = availableCars.get(0);
                        if (selectedCar != null) {
                            Driving driving = mapReservationIntoDriving(reservation, selectedCar);
                            course.getDrivings().add(driving);
                            drivingRepository.save(driving);
                            reservation.setAccepted(true);
                            reservationRepository.save(reservation);
                            log.info("Reservation with id = " + reservationId + " successfully accepted and transformed into Driving");
                            return true;
                        }
                    }
                    log.info("Reservation with id = " + reservationId + " NOT accepted...");
                    return false;
                } else {
                    throw new NoSuchElementException("Course of Reservations Student is not present");
                }
            } else {
                throw new NoSuchElementException("Student of Reservation is not present");
            }
        } else {
            String msg = "Cannot find Reservation with id = " + reservationId;
            log.info(msg);
            throw new NoSuchElementException(msg);
        }
    }

    public Boolean denyReservation(Long reservationId) {
        log.info("Accepting Reservation with id = " + reservationId);

        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            reservation.setAccepted(false);
            reservationRepository.save(reservation);
            return true;
        }
        throw new NoSuchElementException("Cannot find a Reservation with given id = " + reservationId);
    }

    public void deleteReservation(Long id) {
        log.info("Deleting the Reservation with id = " + id);
        Optional<Reservation> reservationToDelete = reservationRepository.findById(id);
        reservationRepository.delete(reservationToDelete.orElseThrow(() ->
                new NoSuchElementException("Cannot DELETE Reservation with given id = " + id)));
    }

    public ReservationDTO getReservation(Long id) {
        log.info("Getting the Reservation with id = " + id);
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        return mapModelToDTO(optionalReservation.orElseThrow(() ->
                new NoSuchElementException("Cannot GET Reservation with given id = " + id)), ReservationDTO.builder().build());
    }

    public List<ReservationDTO> getReservationsByInstructor() {
        User currentUser = authenticationUtil.getCurrentLoggedUser();
        Long currentLoggedUserId = currentUser.getId();
        log.info("Getting the List of Reservations of current logged Instructor with id = " + currentLoggedUserId);
        List<Reservation> listOfInstructorReservations = reservationRepository.findAllByInstructorIdOrderByStartDateDesc(currentLoggedUserId);
        return listOfInstructorReservations.stream()
                .map(reservation -> mapModelToDTO(reservation, ReservationDTO.builder().build())).collect(Collectors.toList());
    }

    public List<ReservationDTO> getReservationsByStudent() {

        User currentUser = authenticationUtil.getCurrentLoggedUser();
        Long currentLoggedUserId = currentUser.getId();
        log.info("Getting the List of Reservations of current logged Student with id = " + currentLoggedUserId);
        List<Reservation> listOfStudentReservations = reservationRepository.findAllByStudentIdOrderByStartDateDesc(currentLoggedUserId);
        return listOfStudentReservations.stream()
                .map(reservation -> mapModelToDTO(reservation, ReservationDTO.builder().build())).collect(Collectors.toList());
    }

    public List<ReservationDTO> getAll() {
        log.info("Getting all Reservations");
        return findAll().stream().map(reservation -> mapModelToDTO(reservation, ReservationDTO.builder().build())).collect(Collectors.toList());
    }

    // -- dao methods --
    public List<Reservation> findAllById(Set<Long> reservationsToAdd) {
        return reservationRepository.findAllById(reservationsToAdd);
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    // -- mapper methods --
    public ReservationDTO mapModelToDTO(Reservation model, ReservationDTO dto) {
        return reservationMapper.mapModelToDTO(model, dto);
    }

    public Reservation mapDTOToModel(ReservationDTO dto, Reservation model) {
        if (dto.getId() != null) {
            Optional<Reservation> optionalReservation = reservationRepository.findById(dto.getId());
            model = optionalReservation.orElse(model);
        }
        model = reservationMapper.mapDTOToModel(dto, model);
        return reservationRepository.save(model);
    }

    public Driving mapReservationIntoDriving(Reservation reservation, Car car) {
        log.info("Mapping reservation into Driving");
        return reservationMapper.mapReservationIntoDriving(reservation, car);
    }

    public Reservation createReservationFromDTO(ReservationDTO dto) {

        User currentUser = authenticationUtil.getCurrentLoggedUser();
        Student student;
        if (currentUser instanceof Student) {
            student = (Student) currentUser;
            Course course = student.getCourse();
            if (course != null) {
                Reservation reservation = Reservation.builder().build();
                reservation.setStudent(student);
                Instant startDate = Instant.parse(dto.getStartDate());

                reservation.setStartDate(startDate);
                reservation.setDuration(dto.getDuration());
                reservation.setFinishDate(startDate.plus(dto.getDuration(), ChronoUnit.MINUTES));

                reservation.setCarBrand(CarBrand.of(dto.getCarBrand().trim()));

                Optional<Instructor> optionalInstructor = instructorService.findById(dto.getInstructor().getId());
                reservation.setInstructor(optionalInstructor.orElseThrow(() ->
                        new NoSuchElementException("Cannot find an Instructor with given Id = " + dto.getInstructor().getId())));

                Optional<DrivingCity> optionalCity = cityService.findByName(dto.getDrivingCity());
                reservation.setDrivingCity(optionalCity.orElseThrow(() ->
                        new NoSuchElementException("Cannot find a Driving City with given name = " + dto.getDrivingCity())));

                course.getReservations().add(reservation);
                return reservation;
            }
        }
        return null;
    }

    private List<Car> extractCarsFromDrivings(List<Driving> drivings) {
        return drivings.stream()
                .map(exam -> exam.getCar())
                .collect(Collectors.toList());
    }

    private List<Car> extractCarsFromPracticalExams(List<PracticalExam> practicalExams) {
        return practicalExams.stream()
                .map(exam -> exam.getCar())
                .collect(Collectors.toList());
    }
}
