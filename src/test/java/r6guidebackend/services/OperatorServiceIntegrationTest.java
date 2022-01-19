package r6guidebackend.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import r6guidebackend.models.Operator;
import r6guidebackend.models.requests.UpdateSingleOperatorRequest;
import r6guidebackend.models.responses.GetListOfNamesResponse;
import r6guidebackend.repositories.IGadgetRepository;
import r6guidebackend.repositories.IOperatorRepository;
import r6guidebackend.repositories.IWeaponRepository;
import r6guidebackend.services.interfaces.IOperatorService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.any;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
public class OperatorServiceIntegrationTest {

    @MockBean
    private IOperatorRepository repository;

    @MockBean
    private IGadgetRepository gadgetRepository;

    @MockBean
    private IWeaponRepository weaponRepository;

    @TestConfiguration
    static class OperatorServiceTestContextConfiguration {
//        @Autowired
//        private IOperatorRepository operatorRepository;
//
//        @MockBean
//        private IGadgetRepository gadgetRepository;
//
//        @MockBean
//        private IWeaponRepository weaponRepository;



        @Bean
        public IOperatorService service() {
            return new OperatorService(
//                    operatorRepository,
////                    repository,
//                    gadgetRepository,
//                    weaponRepository
            );
        }
    }

    @Autowired
    private IOperatorService service;

    @Before
    public void setup() {
        List<Operator> operators = new ArrayList<>();
        operators.add(new Operator("Ace", "Attacker", "SAS"));
        operators.add(new Operator("Ash", "Attacker", "FBI SWAT"));
        operators.add(new Operator("Smoke", "Defender", "SAS"));
        operators.add(new Operator("Kapkan", "Defender", "SPESTNAZ"));
        operators.add(new Operator("Tachankin", "Defender", "SPETSNAZ"));

        given(repository.findAll()).willReturn(operators);
    }

    @Test
    public void getAllNamesTest() throws Exception{
        GetListOfNamesResponse response = service.getAllNames().get();

        List<String> names = response.getNames();

        assertEquals(5, names.size());
        assertEquals("Kapkan", names.get(3));
    }

    @Test
    public void getSingleOperatorTest() throws Exception{
        Operator operator = new Operator("Ace", "Attacker", "SAS");

        given(repository.findOperatorByName(operator.getName())).willReturn(operator);

        Operator response = service.getSingleOperator(operator.getName()).get();

        assertEquals("Ace", response.getName());
    }

    @Test
    public void updateSingleOperatorTest() throws Exception{
        Operator operator = new Operator("Ace", "Attacker", "SAS");

        UpdateSingleOperatorRequest request = new UpdateSingleOperatorRequest();

        request.setBiography("Biography");
        request.setCountryOfBirth("Belarus");

        given(repository.findOperatorByName(operator.getName())).willReturn(operator);
        given(repository.save(operator)).willReturn(operator);

        service.updateSingleOperator(operator.getName(), request);

        assertEquals(request.getBiography(), operator.getBiography());
        assertEquals(request.getCountryOfBirth(), operator.getCountryOfBirth());
    }
}
