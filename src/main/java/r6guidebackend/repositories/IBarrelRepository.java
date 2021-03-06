package r6guidebackend.repositories;

import r6guidebackend.models.Barrel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBarrelRepository extends JpaRepository<Barrel, Integer> {
    Barrel findBarrelByName(String name);
    boolean deleteBarrelByName(String name);
}
