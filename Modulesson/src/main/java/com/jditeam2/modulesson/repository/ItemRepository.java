package com.jditeam2.modulesson.repository;

import com.jditeam2.modulesson.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
