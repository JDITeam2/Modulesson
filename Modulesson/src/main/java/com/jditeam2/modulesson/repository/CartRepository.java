package com.jditeam2.modulesson.repository;

import com.jditeam2.modulesson.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
