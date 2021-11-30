package com.example.r6guidebackend.repositories;

import com.example.r6guidebackend.models.UnderBarrel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUnderBarrelRepository extends JpaRepository<UnderBarrel, Integer> {
}
