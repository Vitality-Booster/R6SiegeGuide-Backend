package r6guidebackend.repositories;

import r6guidebackend.models.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMapRepository extends JpaRepository<Map, Integer> {
    Map findByName(String name);
}
