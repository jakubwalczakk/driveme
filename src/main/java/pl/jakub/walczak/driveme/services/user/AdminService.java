package pl.jakub.walczak.driveme.services.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.user.AdminDTO;
import pl.jakub.walczak.driveme.dto.user.AdminRegistrationDTO;
import pl.jakub.walczak.driveme.mappers.user.AdminMapper;
import pl.jakub.walczak.driveme.mappers.user.RegistrationMapper;
import pl.jakub.walczak.driveme.model.user.Admin;
import pl.jakub.walczak.driveme.repos.user.AdminRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AdminService {

    private AdminRepository adminRepository;
    private AdminMapper adminMapper;
    private RegistrationMapper registrationMapper;

    @Autowired
    public AdminService(AdminRepository adminRepository, AdminMapper adminMapper, RegistrationMapper registrationMapper) {
        this.adminRepository = adminRepository;
        this.adminMapper = adminMapper;
        this.registrationMapper = registrationMapper;
    }

    // -- methods for controller --
    public Admin createAdmin(AdminRegistrationDTO adminRegistrationDTO) {
        log.info("Creating new Admin...");
        Admin admin = registrationMapper.mapRegistrationDTOToInstructor(adminRegistrationDTO);
        return adminRepository.save(admin);
    }

    public void deleteAdmin(Long id) {
        log.info("Deleting the Admin with id = " + id);
        Optional<Admin> adminToDelete = adminRepository.findById(id);
        if (adminToDelete.isPresent()) {
            Admin admin = adminToDelete.get();
            admin.setActive(false);
            adminRepository.save(admin);
        } else {
            throw new NoSuchElementException("Cannot DELETE Admin with given id = " + id);
        }
    }

    public AdminDTO getAdmin(Long id) {
        log.info("Getting the Admin with id = " + id);
        Optional<Admin> optionalAdmin = adminRepository.findById(id);
        return mapModelToDTO(optionalAdmin.orElseThrow(() ->
                new NoSuchElementException("Cannot GET Admin with given id = " + id)), AdminDTO.builder().build());
    }

    public List<AdminDTO> getAll() {
        log.info("Getting all Admins");
        return findAll().stream().map(admin -> mapModelToDTO(admin, AdminDTO.builder().build())).collect(Collectors.toList());
    }

    // -- dao methods --
    public Optional<Admin> findById(Long id) {
        return adminRepository.findById(id);
    }

    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    // -- mapper methods --
    public AdminDTO mapModelToDTO(Admin model, AdminDTO dto) {
        return adminMapper.mapModelToDTO(model, dto);
    }

    public Admin mapDTOToModel(AdminDTO dto, Admin model) {
        if (dto.getId() != null) {
            Optional<Admin> optionalAdmin = adminRepository.findById(dto.getId());
            model = optionalAdmin.orElse(model);
        }
        model = adminMapper.mapDTOToModel(dto, model);
        return adminRepository.save(model);
    }
}
