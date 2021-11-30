package com.example.r6guidebackend.repositories;

import com.example.r6guidebackend.models.Barrel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBarrelRepository extends JpaRepository<Barrel, Integer> {
}
