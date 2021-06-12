package pl.karkowski.empik.service.api;

import pl.karkowski.empik.dto.UserResponse;

/**
 * @author Michal on 12.06.2021
 */
public interface ScmService {

    UserResponse getUser(final String userLogin);
}
