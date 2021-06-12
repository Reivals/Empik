package pl.karkowski.empik.dto;

import lombok.Getter;

/**
 * @author Michal on 12.06.2021
 */
@Getter
public class UserResponse {
    private final Integer id;
    private final String login;
    private final String name;
    private final UserType type;
    private final String avatarUrl;
    private final String createdAt;
    private final Double calculations;

    public UserResponse(final Integer id, final String name, final UserType type,
                        final String avatarUrl, final String createdAt, final Integer followers,
                        final Integer publicRepos, final String login) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.login = login;
        this.avatarUrl = avatarUrl;
        this.createdAt = createdAt;
        if (followers != null && followers != 0) {
            calculations = 6.0 / followers * (2 * publicRepos);
        } else {
            calculations = -1.0;
        }
    }
}
