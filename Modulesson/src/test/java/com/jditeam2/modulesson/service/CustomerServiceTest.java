package com.jditeam2.modulesson.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jditeam2.modulesson.domain.Customer;
import com.jditeam2.modulesson.domain.Expert;
import com.jditeam2.modulesson.dto.CustomerForm;
import com.jditeam2.modulesson.dto.ExpertForm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
@TestPropertySource(properties = "spring.config.location=classpath:application-test.yml")
class CustomerServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    CustomerService customerService;

    @Autowired
    ExpertService expertService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Customer createCustomer() {
        CustomerForm customerForm = CustomerForm.builder()
                .userName("테스트")
                .password("111111")
                .nickname("테스트1")
                .email("test@email.com")
                .phone("01000000000")
                .build();
        return Customer.createCustomer(customerForm, passwordEncoder);
    }


    @Test
    @DisplayName("회원가입 테스트 - Customer")
        public void saveCustomerTest() {
        Customer customer = createCustomer();
        Customer savedCustomer = customerService.saveUser(customer);

        assertEquals(customer.getEmail(), savedCustomer.getEmail());
    }


    @Test
    public Expert createExpert() {
        ExpertForm expertForm = ExpertForm.builder()
                .expertName("테스트")
                .password("111111")
                .nickname("테스트1")
                .email("test@email.com")
                .phone("01000000000")
                .build();
        return Expert.createExpert(expertForm, passwordEncoder);
    }

    @Test
    @DisplayName("회원가입 테스트 - Expert")
        public void saveExpertTest() {
        Expert expert = createExpert();
        Expert savedExpert = expertService.saveExpert(expert);

        assertEquals(expert.getEmail(), savedExpert.getEmail());
    }


}