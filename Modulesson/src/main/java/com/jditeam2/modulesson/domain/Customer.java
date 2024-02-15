package com.jditeam2.modulesson.domain;

import com.jditeam2.modulesson.dto.CustomerForm;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@ToString
@Getter
@Setter
@Table(name = "customer")
public class Customer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @Column(unique = true)
    private String email;
    private String userName;
    private String password;
    private String nickname;
    private String phone;

    @Builder
    public Customer(String userName, String password, String nickname, String email, String phone) {
        this.userName = userName;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.phone = phone;
    }

    public static Customer createCustomer(CustomerForm customerForm, PasswordEncoder passwordEncoder) {
        Customer customer = Customer.builder()
                .userName(customerForm.getUserName())
                .password(passwordEncoder.encode(customerForm.getPassword()))
                .nickname(customerForm.getNickname())
                .email(customerForm.getEmail())
                .phone(customerForm.getPhone())
                .build();
        return customer;
    }
}
