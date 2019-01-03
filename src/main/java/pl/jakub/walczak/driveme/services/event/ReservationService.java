package pl.jakub.walczak.driveme.services.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.event.ReservationDTO;
import pl.jakub.walczak.driveme.enums.CarBrand;
import pl.jakub.walczak.driveme.mappers.event.ReservationMapper;
import pl.jakub.walczak.driveme.model.car.Car;
import pl.jakub.walczak.driveme.model.city.DrivingCity;
import pl.jakub.walczak.driveme.model.event.Driving;
import pl.jakub.walczak.driveme.model.event.Reservation;
import pl.jakub.walczak.driveme.model.user.Instructor;
import pl.jakub.walczak.driveme.model.user.Student;
import pl.jakub.walczak.driveme.model.user.User;
import pl.jakub.walczak.driveme.repos.event.DrivingRepository;
import pl.jakub.walczak.driveme.repos.event.ReservationRepository;
import pl.jakub.walczak.driveme.repos.event.exam.PracticalExamRepository;
import pl.jakub.walczak.driveme.services.car.CarService;
import pl.jakub.walczak.driveme.services.city.CityService;
import pl.jakub.walczak.driveme.services.user.InstructorService;
import pl.jakub.walczak.driveme.utils.AuthenticationUtil;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ReservationService {

    private ReservationRepository reservationRepository;
    private ReservationMapper reservationMapper;
    private AuthenticationUtil authenticationUtil;

    private DrivingRepository drivingRepository;
    private PracticalExamRepository practicalExamRepository;

    private CarService carService;
    private InstructorService instructorService;
    private CityService cityService;

    private final static Integer MINUTE_IN_SECONDS = 60;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, ReservationMapper reservationMapper,
                              AuthenticationUtil authenticationUtil,
                              DrivingRepository drivingRepository, PracticalExamRepository practicalExamRepository,
                              CarService carService, InstructorService instructorService, CityService cityService) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
        this.authenticationUtil = authenticationUtil;
        this.drivingRepository = drivingRepository;
        this.practicalExamRepository = practicalExamRepository;
        this.carService = carService;
        this.instructorService = instructorService;
        this.cityService = cityService;
    }

    // -- methods for controller --
    public Reservation addReservation(ReservationDTO reservationDTO) {
        log.info("Adding new Reservation...");

        log.info(reservationDTO.toString());
        Reservation reservation = createReservationFromDTO(reservationDTO);
        if (reservation != null) {
            log.info("Reservation is NOT null");
        } else {
            log.info("Reservation IS NULL!");
        }

        return reservationRepository.save(reservation);
//        Reservation reservation = mapDTOToModel(reservationDTO, Reservation.builder().build());
//        return reservationRepository.save(reservation);
    }

    public Boolean acceptReservation(Long reservationId) {
        log.info("Accepting Reservation with id = " + reservationId);
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            CarBrand carBrand = reservation.getCarBrand();
            List<Car> cars = carService.findAllCarByBrand(carBrand);

            //FIXME
            //IT WORKS, BUT...
            Set<Car> carsFromDrivingsInternal =
                    drivingRepository
                            .findAllByCarBrandAndStartDateAfterAndFinishDateBefore(carBrand, reservation.getStartDate(), reservation.getFinishDate())
                            .stream()
                            .map(driving -> driving.getCar())
                            .collect(Collectors.toSet());

            Set<Car> carsFromDrivingsBefore =
                    drivingRepository.findAllByCarBrandAndStartDateBeforeAndFinishDateAfter(carBrand, reservation.getStartDate(), reservation.getStartDate())
                            .stream()
                            .map(driving -> driving.getCar())
                            .collect(Collectors.toSet());

            Set<Car> carsFromDrivingsAfter =
                    drivingRepository.findAllByCarBrandAndStartDateBeforeAndFinishDateAfter(carBrand, reservation.getFinishDate(), reservation.getFinishDate())
                            .stream()
                            .map(driving -> driving.getCar())
                            .collect(Collectors.toSet());

            Set<Car> carsFromExamsInternal =
                    practicalExamRepository
                            .findAllByCarBrandAndStartDateAfterAndFinishDateBefore(carBrand, reservation.getStartDate(), reservation.getFinishDate())
                            .stream()
                            .map(exam -> exam.getCar())
                            .collect(Collectors.toSet());

            Set<Car> carsFromExamsBefore =
                    practicalExamRepository.findAllByCarBrandAndStartDateBeforeAndFinishDateAfter(carBrand, reservation.getStartDate(), reservation.getStartDate())
                            .stream()
                            .map(exam -> exam.getCar())
                            .collect(Collectors.toSet());
            Set<Car> carsFromExamsAfter =
                    practicalExamRepository.findAllByCarBrandAndStartDateBeforeAndFinishDateAfter(carBrand, reservation.getFinishDate(), reservation.getFinishDate())
                            .stream()
                            .map(exam -> exam.getCar())
                            .collect(Collectors.toSet());

            Set<Car> carsFromDrivings = new HashSet<>(carsFromDrivingsInternal);
            carsFromDrivings.addAll(carsFromDrivingsBefore);
            carsFromDrivings.addAll(carsFromDrivingsAfter);

            Set<Car> carsFromExams = new HashSet<>(carsFromExamsInternal);
            carsFromExams.addAll(carsFromExamsBefore);
            carsFromExams.addAll(carsFromExamsAfter);

            Set<Car> unavailableCars = new HashSet<>(carsFromDrivings);
            unavailableCars.addAll(carsFromExams);

            Set<Car> availableCars = cars.stream()
                    .filter(car -> !unavailableCars.contains(car)).collect(Collectors.toSet());

            Iterator availableCarsIterator = availableCars.iterator();

            if (availableCarsIterator.hasNext()) {
                Car selectedCar = (Car) availableCarsIterator.next();

                if (selectedCar != null) {

                    Driving driving = mapReservationIntoDriving(reservation, selectedCar);
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
            String msg = "Cannot find Reservation with id = " + reservationId;
            log.info(msg);
            throw new NoSuchElementException(msg);
        }
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
            Reservation reservation = Reservation.builder().build();
            reservation.setStudent(student);
            Instant startDate = Instant.parse(dto.getStartDate());

            reservation.setStartDate(startDate);
            reservation.setDuration(dto.getDuration());
            reservation.setFinishDate(startDate.plusSeconds(dto.getDuration() * MINUTE_IN_SECONDS));

            reservation.setCarBrand(CarBrand.of(dto.getCarBrand().trim()));

            Optional<Instructor> optionalInstructor = instructorService.findByEmail(dto.getInstructor().getEmail());
            reservation.setInstructor(optionalInstructor.orElseThrow(() ->
                    new NoSuchElementException("Cannot find an Instructor with given email = " + dto.getInstructor().getEmail())));

            Optional<DrivingCity> optionalCity = cityService.findByName(dto.getDrivingCity());
            reservation.setDrivingCity(optionalCity.orElse(null));

            return reservation;
        } else {
            return null;
        }
    }
}
