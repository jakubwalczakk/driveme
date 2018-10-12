package pl.jakub.walczak.driveme.services.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.event.DrivingDTO;
import pl.jakub.walczak.driveme.mappers.event.DrivingMapper;
import pl.jakub.walczak.driveme.model.event.Driving;
import pl.jakub.walczak.driveme.repos.event.DrivingRepository;

import java.util.List;
import java.util.Optional;

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
