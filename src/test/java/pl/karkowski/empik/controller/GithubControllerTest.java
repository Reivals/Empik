package pl.karkowski.empik.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.karkowski.empik.helper.TestHelper;
import pl.karkowski.empik.service.api.ScmService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Michal on 12.06.2021
 */
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class GithubControllerTest  {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScmService scmService;

    @Test
    void getUser() throws Exception {
        when(scmService.getUser(any())).thenReturn(TestHelper.getUserResponse());
        mockMvc.perform(MockMvcRequestBuilders
                .get("/users/abc")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}