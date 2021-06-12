package pl.karkowski.empik.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Michal on 11.06.2021
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
public class GithubUserDTO extends ScmUser{
    private Integer id;
    private String login;
    private String name;
    @JsonProperty("type")
    private UserType userType;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    @JsonProperty("created_at")
    private String createdAt;
    private Integer followers;
    @JsonProperty("public_repos")
    private Integer publicRepos;
}
