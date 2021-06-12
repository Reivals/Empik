package pl.karkowski.empik.service.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import pl.karkowski.empik.aspect.IncrementCounter;
import pl.karkowski.empik.config.GithubServiceConfig;
import pl.karkowski.empik.dto.GithubUserDTO;
import pl.karkowski.empik.dto.UserResponse;

import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author Michal on 11.06.2021
 */

@Service
public class GithubService implements ScmService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GithubService.class);

    private final RestTemplate restTemplate;
    private final GithubServiceConfig githubServiceConfig;

    public GithubService(final RestTemplate restTemplate, final GithubServiceConfig githubServiceConfig) {
        this.restTemplate = restTemplate;
        this.githubServiceConfig = githubServiceConfig;
    }

    // https://docs.github.com/en/rest/reference/users#get-a-user
    @IncrementCounter
    public UserResponse getUser(final String userLogin) {
        LOGGER.info("Retrieving user data from Github service with login {}", userLogin);
        if(userLogin == null || userLogin.isBlank()) {
            throw new IllegalArgumentException("User login cannot be null or blank value");
        }
        final URI build = new UrlBuilder().withBaseUrl(githubServiceConfig.getBaseUrl())
                .withSuffixUrl(githubServiceConfig.getUserSuffixUrl() + userLogin).build();
        try {
            final ResponseEntity<GithubUserDTO> response = this.restTemplate.getForEntity(build, GithubUserDTO.class);
            return createUserResponse(response.getBody());
        } catch (final HttpClientErrorException exc) {
            throw new ResponseStatusException(NOT_FOUND, "User with given login " + userLogin + " was not found");
        }
    }

    private UserResponse createUserResponse(final GithubUserDTO githubUserDTO) {
        return new UserResponse(githubUserDTO.getId(),
                githubUserDTO.getName(),
                githubUserDTO.getUserType(),
                githubUserDTO.getAvatarUrl(),
                githubUserDTO.getCreatedAt(),
                githubUserDTO.getFollowers(),
                githubUserDTO.getPublicRepos(),
                githubUserDTO.getLogin());
    }

    private static class UrlBuilder {
        private String baseUrl;
        private String suffixUrl;

        public UrlBuilder withBaseUrl(final String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public UrlBuilder withSuffixUrl(final String suffixUrl) {
            this.suffixUrl = suffixUrl;
            return this;
        }

        public URI build() {
            final String uri = baseUrl + suffixUrl;
            try {
                return new URI(uri);
            } catch (final URISyntaxException exc) {
                throw new IllegalStateException("Error during construction of Github API url", exc);
            }
        }
    }
}
