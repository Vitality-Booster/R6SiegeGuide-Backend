package r6guidebackend.repositories;

import r6guidebackend.models.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOperatorRepository extends JpaRepository<Operator, Integer> {
}
