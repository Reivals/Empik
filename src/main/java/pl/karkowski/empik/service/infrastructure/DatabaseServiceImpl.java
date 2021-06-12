package pl.karkowski.empik.service.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.karkowski.empik.model.UserApiCounter;
import pl.karkowski.empik.repository.UserApiCounterRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author Michal on 12.06.2021
 */

@Service("databaseService")
public class DatabaseServiceImpl implements DatabaseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseServiceImpl.class);

    private final UserApiCounterRepository userApiCounterRepository;

    public DatabaseServiceImpl(final UserApiCounterRepository userApiCounterRepository) {
        this.userApiCounterRepository = userApiCounterRepository;
    }

    @Transactional
    public void incrementUserApiCounter(final String login) {
        LOGGER.info("Trying to increment user api counter for {}", login);
        try {
            final UserApiCounter userApiCounter = getUserApiCounterById(login);
            userApiCounter.incrementCounter();
            saveUserApiCounter(userApiCounter);
        } catch(final EntityNotFoundException exc) {
            final UserApiCounter userApiCounter = new UserApiCounter();
            userApiCounter.setLogin(login);
            saveUserApiCounter(userApiCounter);
        }
    }

    private void saveUserApiCounter(final UserApiCounter userApiCounter) {
        LOGGER.info("Saving {} to database", userApiCounter);
        userApiCounterRepository.save(userApiCounter);
    }

    private UserApiCounter getUserApiCounterById(final String login) throws EntityNotFoundException {
        final Optional<UserApiCounter> optionalUserApiCounter = userApiCounterRepository.findById(login);
        return optionalUserApiCounter.orElseThrow(
                () -> new EntityNotFoundException("User Api counter with name " + login + " was not found"));
    }

}
