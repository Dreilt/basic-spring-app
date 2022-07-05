package pl.dreilt.basicspringapp.repository;

import org.springframework.data.repository.CrudRepository;
import pl.dreilt.basicspringapp.model.AppUser;

import java.util.Optional;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);
}
