package com.example.producingwebservice.repositories;

import com.example.producingwebservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    void deleteByUsername(String username);
    User findByUsername(String username);
}
