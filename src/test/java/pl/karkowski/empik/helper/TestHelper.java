package pl.karkowski.empik.helper;

import org.assertj.core.util.diff.Delta;
import pl.karkowski.empik.dto.GithubUserDTO;
import pl.karkowski.empik.dto.UserResponse;
import pl.karkowski.empik.dto.UserType;

/**
 * @author Michal on 12.06.2021
 */

public class TestHelper {
    public static final String NAME = "Reivals";
    public static final String LOGIN = "Reivals123";
    public static final String AVATAR_URL = "avatar.com";
    public static final UserType USER_TYPE = UserType.User;
    public static final String CREATED_AT = "Reivals";
    public static final Integer FOLLOWERS = 231;
    public static final Integer ID = 321;
    public static final Integer PUBLIC_REPOS = 123;

    public static GithubUserDTO getMockGithubUserDTO() {
        final GithubUserDTO mockDTO = new GithubUserDTO();
        mockDTO.setAvatarUrl(AVATAR_URL);
        mockDTO.setUserType(USER_TYPE);
        mockDTO.setName(NAME);
        mockDTO.setCreatedAt(CREATED_AT);
        mockDTO.setFollowers(FOLLOWERS);
        mockDTO.setId(ID);
        mockDTO.setLogin(LOGIN);
        mockDTO.setPublicRepos(PUBLIC_REPOS);
        return mockDTO;
    }

    public static UserResponse getUserResponse() {
        return new UserResponse(
                ID,
                NAME,
                USER_TYPE,
                AVATAR_URL,
                CREATED_AT,
                FOLLOWERS,
                PUBLIC_REPOS,
                LOGIN
        );
    }
}
