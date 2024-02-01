package com.jditeam2.modulesson.repository;

import com.jditeam2.modulesson.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByUsername(String username);
}
