package pl.jakub.walczak.driveme.services.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.event.DrivingDTO;
import pl.jakub.walczak.driveme.mappers.event.DrivingMapper;
import pl.jakub.walczak.driveme.model.event.CalendarEvent;
import pl.jakub.walczak.driveme.model.event.Driving;
import pl.jakub.walczak.driveme.repos.event.DrivingRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DrivingService {

    private DrivingRepository drivingRepository;
    private DrivingMapper drivingMapper;

    @Autowired
    public DrivingService(DrivingRepository drivingRepository, DrivingMapper drivingMapper) {
        this.drivingRepository = drivingRepository;
        this.drivingMapper = drivingMapper;
    }

    // -- methods for controller --
    public Driving addDriving(DrivingDTO drivingDTO) {
        Driving driving = mapDTOToModel(drivingDTO, Driving.builder().build());
        return drivingRepository.save(driving);
    }

    public void deleteDriving(Long id) {
        Optional<Driving> drivingToDelete = drivingRepository.findById(id);
        if (drivingToDelete.isPresent()) {
            drivingRepository.delete(drivingToDelete.get());
        } else {
            throw new NoSuchElementException();
        }
    }

    public DrivingDTO getDriving(Long id) {
        Optional<Driving> optionalDriving = drivingRepository.findById(id);
        if (optionalDriving.isPresent()) {
            return mapModelToDTO(optionalDriving.get(), DrivingDTO.builder().build());
        } else {
            throw new NoSuchElementException();
        }
    }

    public List<DrivingDTO> getAll() {
        return findAll().stream().map(driving -> mapModelToDTO(driving, DrivingDTO.builder().build())).collect(Collectors.toList());
    }

    // -- dao methods --
    public List<Driving> findAll() {
        return drivingRepository.findAll();
    }

    // -- mapper methods --
    public DrivingDTO mapModelToDTO(Driving model, DrivingDTO dto) {
        return drivingMapper.mapModelToDTO(model, dto);
    }

    public Driving mapDTOToModel(DrivingDTO dto, Driving model) {
        if (dto.getId() != null) {
            Optional<Driving> optionalDriving = drivingRepository.findById(dto.getId());
            if (optionalDriving.isPresent()) {
                model = optionalDriving.get();
            }
        }
        model = drivingMapper.mapDTOToModel(dto, model);
        return drivingRepository.save(model);
    }
}
