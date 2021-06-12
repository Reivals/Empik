package pl.karkowski.empik.db;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import pl.karkowski.empik.model.UserApiCounter;
import pl.karkowski.empik.repository.UserApiCounterRepository;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author Michal on 12.06.2021
 */
@SpringBootTest
@Transactional
public class InMemoryDBTest {
    private static final String LOGIN = "heheszky";

    @Autowired
    private UserApiCounterRepository repository;

    @Test
    public void persistAndFindUser() {
        UserApiCounter toPersist = new UserApiCounter();
        toPersist.setLogin(LOGIN);
        repository.save(toPersist);
        Optional<UserApiCounter> response = repository.findById(toPersist.getLogin());
        UserApiCounter userApiCounter = response.get();
        Assertions.assertNotNull(userApiCounter);
        Assertions.assertEquals(LOGIN, userApiCounter.getLogin());
        Assertions.assertEquals(0, userApiCounter.getRequestCount());
    }
}
