package pl.karkowski.empik.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pl.karkowski.empik.config.GithubServiceConfig;
import pl.karkowski.empik.dto.UserResponse;
import pl.karkowski.empik.helper.TestHelper;
import pl.karkowski.empik.service.api.GithubService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Michal on 12.06.2021
 */
@ExtendWith(MockitoExtension.class)
class GithubServiceTest {
    @InjectMocks
    private GithubService githubService;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private GithubServiceConfig githubServiceConfig;

    @Test
    void getUser() {
        final ResponseEntity responseEntity = new ResponseEntity(TestHelper.getMockGithubUserDTO(), HttpStatus.ACCEPTED);
        when(githubServiceConfig.getBaseUrl()).thenReturn("https://abc.pl");
        when(githubServiceConfig.getUserSuffixUrl()).thenReturn("/def");
        when(restTemplate.getForEntity(any(), any())).thenReturn(responseEntity);
        final UserResponse user = githubService.getUser(TestHelper.NAME);
        assertEquals(TestHelper.AVATAR_URL, user.getAvatarUrl());
        assertEquals(TestHelper.USER_TYPE, user.getType());
        assertEquals(TestHelper.NAME, user.getName());
        assertEquals(TestHelper.CREATED_AT, user.getCreatedAt());
        assertEquals(TestHelper.ID, user.getId());
    }

    @Test
    void getUserBlankValue() {
        assertThrows(IllegalArgumentException.class, () -> githubService.getUser("      "));
    }

    @Test
    void getUserEmptyValue() {
        assertThrows(IllegalArgumentException.class, () -> githubService.getUser(""));
    }



}