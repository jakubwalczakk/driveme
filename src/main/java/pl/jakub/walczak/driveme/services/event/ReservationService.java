package pl.jakub.walczak.driveme.services.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.event.ReservationDTO;
import pl.jakub.walczak.driveme.enums.CarBrand;
import pl.jakub.walczak.driveme.mappers.event.ReservationMapper;
import pl.jakub.walczak.driveme.model.car.Car;
import pl.jakub.walczak.driveme.model.event.Driving;
import pl.jakub.walczak.driveme.model.event.Reservation;
import pl.jakub.walczak.driveme.model.user.User;
import pl.jakub.walczak.driveme.repos.car.CarRepository;
import pl.jakub.walczak.driveme.repos.event.DrivingRepository;
import pl.jakub.walczak.driveme.repos.event.ReservationRepository;
import pl.jakub.walczak.driveme.services.user.InstructorService;
import pl.jakub.walczak.driveme.utils.AuthenticationUtil;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ReservationService {

    private ReservationRepository reservationRepository;
    private ReservationMapper reservationMapper;
    private AuthenticationUtil authenticationUtil;

    private DrivingRepository drivingRepository;

    private CarRepository carRepository;
    private InstructorService instructorService;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, ReservationMapper reservationMapper,
                              AuthenticationUtil authenticationUtil,
                              DrivingRepository drivingRepository, CarRepository carRepository, InstructorService instructorService) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
        this.authenticationUtil = authenticationUtil;
        this.drivingRepository = drivingRepository;
        this.carRepository = carRepository;
        this.instructorService = instructorService;
    }

    // -- methods for controller --
    public Reservation addReservation(ReservationDTO reservationDTO) {
        log.info("Adding new Reservation...");
        Reservation reservation = mapDTOToModel(reservationDTO, Reservation.builder().build());
        return reservationRepository.save(reservation);
    }

    public Boolean acceptReservation(Long reservationId) {
        log.info("Accepting Reservation with id = " + reservationId);
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            CarBrand carBrand = reservation.getCarBrand();
            //FIXME
            //find available car with specified brand in given terms
            List<Car> cars = carRepository.findAllCarByBrand(carBrand);
            Driving driving = mapReservationIntoDriving(reservation, cars.get(0));
            drivingRepository.save(driving);
            return true;
        } else {
            String msg = "Cannot find Reservation with id = " + reservationId;
            log.info(msg);
            throw new NoSuchElementException(msg);
        }
    }

    public void deleteReservation(Long id) {
        log.info("Deleting the Reservation with id = " + id);
        Optional<Reservation> reservationToDelete = reservationRepository.findById(id);
        if (reservationToDelete.isPresent()) {
            reservationRepository.delete(reservationToDelete.get());
        } else {
            throw new NoSuchElementException("Cannot DELETE Reservation with given id = " + id);
        }
    }

    public ReservationDTO getReservation(Long id) {
        log.info("Getting the Reservation with id = " + id);
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if (optionalReservation.isPresent()) {
            return mapModelToDTO(optionalReservation.get(), ReservationDTO.builder().build());
        } else {
            throw new NoSuchElementException("Cannot GET Reservation with given id = " + id);
        }
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
            if (optionalReservation.isPresent()) {
                model = optionalReservation.get();
            }
        }
        model = reservationMapper.mapDTOToModel(dto, model);
        return reservationRepository.save(model);
    }

    public Driving mapReservationIntoDriving(Reservation reservation, Car car) {
        log.info("Mapping reservation into Driving");
        return reservationMapper.mapReservationIntoDriving(reservation, car);
    }
}
