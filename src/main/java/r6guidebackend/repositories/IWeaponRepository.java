package r6guidebackend.repositories;

import r6guidebackend.models.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWeaponRepository extends JpaRepository<Weapon, Integer> {
    Weapon findWeaponByName(String name);
    boolean deleteWeaponByName(String name);
}
