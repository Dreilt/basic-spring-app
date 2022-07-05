package pl.dreilt.basicspringapp.service;

import org.springframework.stereotype.Service;
import pl.dreilt.basicspringapp.dto.AppUserCredentialsDto;
import pl.dreilt.basicspringapp.mapper.AppUserCredentialsDtoMapper;
import pl.dreilt.basicspringapp.repository.AppUserRepository;

import java.util.Optional;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public Optional<AppUserCredentialsDto> findAppUserCredentialsByEmail(String email) {
        return appUserRepository.findByEmail(email)
                .map(AppUserCredentialsDtoMapper::mapToAppUserCredentialsDto);
    }
}
