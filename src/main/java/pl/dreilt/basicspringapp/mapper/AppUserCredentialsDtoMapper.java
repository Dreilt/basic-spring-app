package pl.dreilt.basicspringapp.mapper;

import pl.dreilt.basicspringapp.dto.AppUserCredentialsDto;
import pl.dreilt.basicspringapp.model.AppUser;
import pl.dreilt.basicspringapp.model.AppUserRole;

import java.util.Set;
import java.util.stream.Collectors;

public class AppUserCredentialsDtoMapper {

    public static AppUserCredentialsDto mapToAppUserCredentialsDto(AppUser appUser) {
        String email = appUser.getEmail();
        String password = appUser.getPassword();
        Set<String> roles = appUser.getRoles()
                .stream()
                .map(AppUserRole::getName)
                .collect(Collectors.toSet());
        return new AppUserCredentialsDto(email, password, roles);
    }
}
