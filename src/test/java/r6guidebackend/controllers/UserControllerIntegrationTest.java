package r6guidebackend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import r6guidebackend.models.requests.RegisterRequest;
import r6guidebackend.models.responses.CustomTokenResponse;
import r6guidebackend.repositories.IUserRepository;
import r6guidebackend.services.UserService;
import r6guidebackend.services.interfaces.IUserService;

import java.util.concurrent.CompletableFuture;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private IUserService service;

    @MockBean
    private IUserRepository repository;

    @Test
    public void registerResponseTest() throws Exception{
        RegisterRequest request = new RegisterRequest();
        request.setEmail("some@email.com");
        request.setPassword("password");
        request.setUsername("Username");
        request.setFullName("Some User");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(request );

        CustomTokenResponse response = new CustomTokenResponse();
        response.setToken("CustomTokenReturn");

        System.out.println("in tests: " + response.getToken());
        given(service.registerUser(request)).willReturn(CompletableFuture.completedFuture(response));

        mvc.perform(post("/users/register", request)
                .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['token']", is(response.getToken())));
    }

}
