package com.jditeam2.modulesson.repository;

import com.jditeam2.modulesson.domain.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ExpertRepository extends JpaRepository<Expert, Long> {
    Expert findByEmail(String email);
    Expert findByExpertName(String expertName);
}
