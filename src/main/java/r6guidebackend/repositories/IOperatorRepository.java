package r6guidebackend.repositories;

import r6guidebackend.models.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOperatorRepository extends JpaRepository<Operator, Integer> {
    Operator findOperatorByName(String name);
    boolean deleteOperatorByName(String name);
    List<Operator> findAllBySpecialUnit(String specialUnit);
    List<Operator> findAllBySide(String side);
}
