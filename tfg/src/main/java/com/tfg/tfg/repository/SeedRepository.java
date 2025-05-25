package com.tfg.tfg.repository;

import com.tfg.tfg.entities.Seed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeedRepository extends JpaRepository<Seed, Long> {
    List<Seed> findByUserId(Long userId);

}
