package pl.karkowski.empik.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Michal on 11.06.2021
 */
@Configuration
@PropertySource("application.properties")
public class GithubServiceConfig {

    private final String baseUrl;
    private final String userSuffixUrl;

    public GithubServiceConfig(@Value( "${github.baseUrl}" ) final String baseUrl,
                               @Value( "${github.userSuffixUrl}" ) final String userSuffixUrl) {
        this.baseUrl = baseUrl;
        this.userSuffixUrl = userSuffixUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getUserSuffixUrl() {
        return userSuffixUrl;
    }
}
