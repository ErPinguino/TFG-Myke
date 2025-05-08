package com.tfg.tfg.Repository;

import com.tfg.tfg.Entities.Seed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeedRepository extends JpaRepository<Seed, Long> {
    List<Seed> findByUserId(Long userId);

}
