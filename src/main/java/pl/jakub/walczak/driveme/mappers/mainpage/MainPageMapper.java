package pl.jakub.walczak.driveme.mappers.mainpage;

import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.mainpage.MainPageDTO;
import pl.jakub.walczak.driveme.dto.user.UserBasicDTO;
import pl.jakub.walczak.driveme.model.user.User;

@Component
public class MainPageMapper {


    public MainPageDTO mapModelToDTO(User user) {
        UserBasicDTO userBasicDTO = UserBasicDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .userRole(user.getUserRole().getValue())
                .build();
        MainPageDTO mainPageDTO = MainPageDTO.builder().user(userBasicDTO).build();
        return mainPageDTO;
    }
}
