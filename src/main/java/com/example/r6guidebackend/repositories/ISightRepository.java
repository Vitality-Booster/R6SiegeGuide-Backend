package com.example.r6guidebackend.repositories;

import com.example.r6guidebackend.models.Sight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISightRepository extends JpaRepository<Sight, Integer> {
}
