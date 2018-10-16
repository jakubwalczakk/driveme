package pl.jakub.walczak.driveme.services.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.event.ReservationDTO;
import pl.jakub.walczak.driveme.mappers.event.ReservationMapper;
import pl.jakub.walczak.driveme.model.event.Reservation;
import pl.jakub.walczak.driveme.repos.event.ReservationRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private ReservationRepository reservationRepository;
    private ReservationMapper reservationMapper;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
    }

    // -- methods for controller --
    public Reservation addReservation(ReservationDTO reservationDTO) {
        Reservation reservation = mapDTOToModel(reservationDTO, Reservation.builder().build());
        return reservationRepository.save(reservation);
    }

    public void deleteReservation(Long id) {
        Optional<Reservation> reservationToDelete = reservationRepository.findById(id);
        if (reservationToDelete.isPresent()) {
            reservationRepository.delete(reservationToDelete.get());
        } else {
            throw new NoSuchElementException();
        }
    }

    public ReservationDTO getReservation(Long id) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if (optionalReservation.isPresent()) {
            return mapModelToDTO(optionalReservation.get(), ReservationDTO.builder().build());
        } else {
            throw new NoSuchElementException();
        }
    }

    public List<ReservationDTO> getAll() {
        return findAll().stream().map(reservation -> mapModelToDTO(reservation, ReservationDTO.builder().build())).collect(Collectors.toList());
    }

    // -- dao methods --
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
