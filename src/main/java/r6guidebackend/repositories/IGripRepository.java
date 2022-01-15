package r6guidebackend.repositories;

import r6guidebackend.models.Grip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGripRepository extends JpaRepository<Grip, Integer> {
    Grip findGripByName(String name);
    boolean deleteGripByName(String name);
}
