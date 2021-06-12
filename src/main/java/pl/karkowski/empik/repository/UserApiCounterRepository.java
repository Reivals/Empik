package pl.karkowski.empik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.karkowski.empik.model.UserApiCounter;

/**
 * @author Michal on 12.06.2021
 */

public interface UserApiCounterRepository extends JpaRepository<UserApiCounter, String> {
}
