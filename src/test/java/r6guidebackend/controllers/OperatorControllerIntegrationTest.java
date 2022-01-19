package r6guidebackend.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import r6guidebackend.models.responses.GetListOfNamesResponse;
import r6guidebackend.repositories.IOperatorRepository;
import r6guidebackend.services.interfaces.IOperatorService;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OperatorController.class)
public class OperatorControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private IOperatorService service;

    @MockBean
    private IOperatorRepository repository;

    @Test
    public void getAllNamesTest() throws Exception {
        GetListOfNamesResponse response = new GetListOfNamesResponse();
        ArrayList<String> names = new ArrayList<>();
        names.add("Ace");
        names.add("Zero");
        response.setNames(names);

        given(service.getAllNames()).willReturn(CompletableFuture.completedFuture(response));

        mvc.perform(get("/operators/get-all-names")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['names'].size()", is(names.size())))
                .andExpect(jsonPath("$['names'][1]", is(names.get(1))));

    }

//    @Test
//    public void getSingleOperatorTest() throws Exception{
//        String name = "Ace";
//
//    }
}
