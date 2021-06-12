package pl.karkowski.empik.aspect;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pl.karkowski.empik.config.GithubServiceConfig;
import pl.karkowski.empik.helper.TestHelper;
import pl.karkowski.empik.service.api.ScmService;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Michal on 12.06.2021
 */
@SpringBootTest
@Import(AnnotationAwareAspectJAutoProxyCreator.class)
class ScmUsernameRetrievalAspectTest {

    @Autowired
    private ScmService scmService;

    @SpyBean
    private ScmUsernameRetrievalAspect apect;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private GithubServiceConfig githubServiceConfig;

    @BeforeEach
    public void setup() {
        final ResponseEntity responseEntity = new ResponseEntity(TestHelper.getMockGithubUserDTO(), HttpStatus.ACCEPTED);
        when(githubServiceConfig.getBaseUrl()).thenReturn("https://abc.pl");
        when(githubServiceConfig.getUserSuffixUrl()).thenReturn("/def");
        when(restTemplate.getForEntity(any(), any())).thenReturn(responseEntity);

    }

    @Test
    public void aspectedTriggeredOnRead() throws Throwable {
        scmService.getUser("abc");
        Mockito.verify(apect, Mockito.times(2)).afterReturn(any());
    }

    @Test
    public void aspectNotTriggeredOnException() throws Throwable {
        assertThrows(IllegalArgumentException.class, () -> scmService.getUser(null));
        Mockito.verify(apect, Mockito.times(0)).afterReturn(any());
    }
}