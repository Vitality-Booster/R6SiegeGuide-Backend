package r6guidebackend.repositories;

import r6guidebackend.models.Gadget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGadgetRepository extends JpaRepository<Gadget, Integer> {
}
