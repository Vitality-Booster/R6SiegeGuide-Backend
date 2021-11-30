package com.example.r6guidebackend.repositories;

import com.example.r6guidebackend.models.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWeaponRepository extends JpaRepository<Weapon, Integer> {
}
