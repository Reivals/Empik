package pl.karkowski.empik.service.infrastructure;

/**
 * @author Michal on 12.06.2021
 */
public interface DatabaseService {

    void incrementUserApiCounter(final String login);
}
