package pl.jakub.walczak.driveme.mappers.mainpage;

import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.mainpage.MainPageDTO;
import pl.jakub.walczak.driveme.model.user.User;

@Component
public class MainPageMapper {


    public MainPageDTO mapModelToDTO(User user, MainPageDTO mainPageDTO) {
        return mainPageDTO;
    }
}
