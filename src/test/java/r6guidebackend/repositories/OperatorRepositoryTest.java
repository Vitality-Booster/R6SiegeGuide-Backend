package r6guidebackend.repositories;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import r6guidebackend.models.Operator;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OperatorRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Before
    public void setup() {
        entityManager.persist(new Operator("Ace", "Attacker", "SAS"));
        entityManager.persist(new Operator("Ash", "Attacker", "FBI SWAT"));
        entityManager.persist(new Operator("Smoke", "Defender", "SAS"));
        entityManager.persist(new Operator("Kapkan", "Defender", "SPETSNAZ"));
        entityManager.persist(new Operator("Tachankin", "Defender", "SPETSNAZ"));
        entityManager.flush();
    }

    @Autowired
    private IOperatorRepository repository;

    @Test
    public void getAllOperatorsTest() {
        List<Operator> operators = repository.findAll();

        assertEquals(5, operators.size());
        assertEquals("Kapkan", operators.get(3).getName());
    }

    @Test
    public void getAllOperatorsFromSpecialUnitTest() {
        String specialUnit = "SPETSNAZ";

        List<Operator> operators = repository.findAllBySpecialUnit(specialUnit);

        assertEquals(2, operators.size());
        assertEquals("Kapkan", operators.get(0).getName());
    }

    @Test
    public void getAllOperatorsFromOneSideTest() {
        String side = "Defender";

        List<Operator> operators = repository.findAllBySide(side);

        assertEquals(3, operators.size());
        assertEquals("Kapkan", operators.get(1).getName());
    }

    @Test
    public void getSingleOperator() {
        String name = "Smoke";

        Operator operator = repository.findOperatorByName(name);

        assertEquals(name, operator.getName());
        assertEquals("SAS", operator.getSpecialUnit());
    }
}
