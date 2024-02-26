package com.example.day_08.repository;

import com.example.day_08.entity.User;
import com.example.day_08.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByRole(UserRole role);
    Optional<User> findByEmail(String email);
    boolean existEmail(String email);
}
