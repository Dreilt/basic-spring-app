package pl.dreilt.basicspringapp.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.dreilt.basicspringapp.dto.AppUserCredentialsDto;
import pl.dreilt.basicspringapp.dto.AppUserRegistrationDto;
import pl.dreilt.basicspringapp.mapper.AppUserCredentialsDtoMapper;
import pl.dreilt.basicspringapp.model.AppUser;
import pl.dreilt.basicspringapp.model.AppUserRole;
import pl.dreilt.basicspringapp.repository.AppUserRepository;
import pl.dreilt.basicspringapp.repository.AppUserRoleRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AppUserService {
    protected final Log logger = LogFactory.getLog(this.getClass());
    private static final String USER_ROLE = "USER";
    private final AppUserRepository appUserRepository;
    private final AppUserRoleRepository appUserRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserService(AppUserRepository appUserRepository, AppUserRoleRepository appUserRoleRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.appUserRoleRepository = appUserRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<AppUserCredentialsDto> findAppUserCredentialsByEmail(String email) {
        return appUserRepository.findByEmail(email)
                .map(AppUserCredentialsDtoMapper::mapToAppUserCredentialsDto);
    }

    public void register(AppUserRegistrationDto appUserRegistrationDto) {
        AppUser appUser = new AppUser();
        appUser.setFirstName(appUserRegistrationDto.getFirstName());
        appUser.setLastName(appUserRegistrationDto.getLastName());
        appUser.setEmail(appUserRegistrationDto.getEmail());
        String passwordHash = passwordEncoder.encode(appUserRegistrationDto.getPassword());
        appUser.setPassword(passwordHash);
        Optional<AppUserRole> userRole = appUserRoleRepository.findByName(USER_ROLE);
        userRole.ifPresentOrElse(
                role -> appUser.getRoles().add(role),
                () -> {
                    logger.error(String.format("Role with name %s not found", USER_ROLE));
                    throw new NoSuchElementException("Invalid role: " + USER_ROLE);
                }
        );
        appUserRepository.save(appUser);
    }
}
