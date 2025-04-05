package com.tfg.tfg.Repository;

import com.tfg.tfg.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
