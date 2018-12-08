package pl.jakub.walczak.driveme.services.mainpage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.mainpage.MainPageDTO;
import pl.jakub.walczak.driveme.mappers.mainpage.MainPageMapper;
import pl.jakub.walczak.driveme.utils.AuthenticationUtil;

import java.util.NoSuchElementException;

@Service
public class MainPageService {

    private AuthenticationUtil authenticationUtil;
    private MainPageMapper mainPageMapper;

    @Autowired
    public MainPageService(AuthenticationUtil authenticationUtil, MainPageMapper mainPageMapper) {
        this.authenticationUtil = authenticationUtil;
        this.mainPageMapper = mainPageMapper;
    }

    public MainPageDTO getMainPage() {
        try {
            return mainPageMapper.mapModelToDTO(authenticationUtil.getCurrentLoggedUser(), MainPageDTO.builder().build());
        } catch (Exception e) {
            throw new NoSuchElementException();
        }
    }
}
