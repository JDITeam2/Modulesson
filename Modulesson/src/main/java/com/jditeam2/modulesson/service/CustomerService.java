package com.jditeam2.modulesson.service;

import com.jditeam2.modulesson.repository.ExpertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import com.jditeam2.modulesson.domain.Customer;
import com.jditeam2.modulesson.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CustomerService implements UserDetailsService {
    private final CustomerRepository customerRepository;

    public Customer saveUser(Customer customer) {
        validateDuplicateUser(customer);

        return customerRepository.save(customer);
    }

    private void validateDuplicateUser(Customer customer) {
        Customer findCustomer = customerRepository.findByEmail(customer.getEmail());
        if (findCustomer != null) {
            throw new IllegalArgumentException("이미 가입된 회원입니다.");
        }
    }

    public void updateUserInfo(String userName, String nickname, String email, String phoneNumber) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer != null) {
            customer.setUserName(userName);
            customer.setNickname(nickname);
            customer.setEmail(email);
            customer.setPhone(phoneNumber);
            customerRepository.save(customer);
        }
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(email);

        if (customer == null) {
            throw new UsernameNotFoundException(email);
        }

        return User
                .builder()
                .username(customer.getEmail())
                .password(customer.getPassword())
                .build();
    }
}
