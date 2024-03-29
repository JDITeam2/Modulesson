package com.jditeam2.modulesson.repository;

import com.jditeam2.modulesson.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByEmail(String email);
    Customer findByUserName(String userName);
}
