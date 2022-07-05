package pl.dreilt.basicspringapp.repository;

import org.springframework.data.repository.CrudRepository;
import pl.dreilt.basicspringapp.model.AppUserRole;

import java.util.Optional;

public interface AppUserRoleRepository extends CrudRepository<AppUserRole, Long> {

    Optional<AppUserRole> findByName(String name);
}
