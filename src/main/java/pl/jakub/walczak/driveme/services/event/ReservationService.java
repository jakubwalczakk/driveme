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

    public ReservationDTO getReservation(Long id) {
        log.info("Getting the Reservation with id = " + id);
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if (optionalReservation.isPresent()) {
            return mapModelToDTO(optionalReservation.get(), ReservationDTO.builder().build());
        } else {
            throw new NoSuchElementException("Cannot GET Reservation with given id = " + id);
        }
    }

    public List<ReservationDTO> getReservationsByInstructor(Long instructorId) {
        log.info("Getting the List of Reservations of Instructor with id = " + instructorId);
        List<Reservation> listOfInstructorReservations = reservationRepository.findAllByInstructorIdOrderByFinishDateDesc(instructorId);
        return listOfInstructorReservations.stream()
                .map(reservation -> mapModelToDTO(reservation, ReservationDTO.builder().build())).collect(Collectors.toList());
    }

    public List<ReservationDTO> getReservationsByStudent(Long studentId) {
        log.info("Getting the List of Reservatiions of Student with id = " + studentId);
        List<Reservation> listOfStudentReservations = reservationRepository.findAllByStudentIdOrderByFinishDateDesc(studentId);
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
}
