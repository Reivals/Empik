package pl.karkowski.empik.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.karkowski.empik.dto.UserResponse;
import pl.karkowski.empik.service.infrastructure.DatabaseService;

/**
 * @author Michal on 12.06.2021
 */
@Aspect
@Component
public class ScmUsernameRetrievalAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScmUsernameRetrievalAspect.class);

    private final DatabaseService databaseService;

    public ScmUsernameRetrievalAspect(final DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @AfterReturning(pointcut = "@annotation(IncrementCounter)", returning = "retVal")
    public void afterReturn(final UserResponse retVal) {
        databaseService.incrementUserApiCounter(retVal.getLogin());
        LOGGER.info("Incremented api invocations for user {}", retVal.getLogin());
    }

}
