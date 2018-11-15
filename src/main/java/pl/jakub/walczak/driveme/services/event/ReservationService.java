package pl.jakub.walczak.driveme.services.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.event.ReservationDTO;
import pl.jakub.walczak.driveme.mappers.event.ReservationMapper;
import pl.jakub.walczak.driveme.model.car.Car;
import pl.jakub.walczak.driveme.model.event.Reservation;
import pl.jakub.walczak.driveme.model.user.Instructor;
import pl.jakub.walczak.driveme.repos.event.ReservationRepository;
import pl.jakub.walczak.driveme.services.car.CarService;
import pl.jakub.walczak.driveme.services.user.InstructorService;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ReservationService {

    private ReservationRepository reservationRepository;
    private ReservationMapper reservationMapper;

    private CarService carService;
    private InstructorService instructorService;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, ReservationMapper reservationMapper,
                              CarService carService, InstructorService instructorService) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
        this.carService = carService;
        this.instructorService = instructorService;
    }

    // -- methods for controller --
    public Reservation addReservation(ReservationDTO reservationDTO) {
        log.info("Adding new Reservation...");
        Reservation reservation = mapDTOToModel(reservationDTO, Reservation.builder().build());
        return reservationRepository.save(reservation);
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

    public Set<Reservation> isTermAvailable(ReservationDTO reservationDTO) {
        log.info("Hello it's me");
        Optional<Car> optionalCar = carService.findById(reservationDTO.getCar().getId());
        Car car;
        if (optionalCar.isPresent()) {
            car = optionalCar.get();
        } else {
            return null;
        }
        Instructor instructor;
        Optional<Instructor> optionalInstructor = instructorService.findById(reservationDTO.getInstructor().getId());
        if (optionalInstructor.isPresent()) {
            instructor = optionalInstructor.get();
        } else {            return null;

        }

        Set<Reservation> allByCarAndInstructor =
                reservationRepository.findAllByCarAndInstructor(car, instructor);

        //FIXME
        for (Reservation reservation : allByCarAndInstructor) {
            log.info(reservation.toString());
        }

        return allByCarAndInstructor;
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

    public List<ReservationDTO> getAll() {
        log.info("Getting all Reservations");
        return findAll().stream().map(reservation -> mapModelToDTO(reservation, ReservationDTO.builder().build())).collect(Collectors.toList());
    }

    // -- dao methods --
    public Set<Reservation> findAllById(Set<Long> reservationsToAdd) {
        return new HashSet<>(reservationRepository.findAllById(reservationsToAdd));
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
}
