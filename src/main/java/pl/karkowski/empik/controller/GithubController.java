package pl.karkowski.empik.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.karkowski.empik.dto.UserResponse;
import pl.karkowski.empik.service.api.ScmService;

/**
 * @author Michal on 11.06.2021
 */

@RestController("/")
public class GithubController {

    private final ScmService scmService;


    public GithubController(final ScmService scmService) {
        this.scmService = scmService;
    }

    @GetMapping(value = "/users/{login}", produces = "application/json")
    public ResponseEntity<UserResponse> getUser(@PathVariable(name = "login") final String userLogin) {
        return new ResponseEntity<>(scmService.getUser(userLogin), HttpStatus.OK);
    }

}
