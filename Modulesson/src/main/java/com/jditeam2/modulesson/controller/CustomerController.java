package com.jditeam2.modulesson.controller;

import com.jditeam2.modulesson.domain.Customer;
import com.jditeam2.modulesson.domain.detail.CustomUserDetailsCustomer;
import com.jditeam2.modulesson.dto.CustomerForm;
import com.jditeam2.modulesson.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class CustomerController {

    @Autowired
    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/new")
    public String userForm(Model model) {
        model.addAttribute("customerFormDto", new CustomerForm());
        return "customer/customerForm";
    }

    @PostMapping(value = "newUser")
    public String userForm(@Valid CustomerForm customerForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "customer/customerForm";
        }

        try {
            Customer customer = Customer.createCustomer(customerForm, passwordEncoder);
            customerService.saveUser(customer);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "customer/customerForm";
        }
        return "redirect:/";
    }

    @GetMapping(value = "/login")
    public String loginCustomer() {
        return "/customer/customerLoginForm";
    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
        return "/customer/customerLoginForm";
    }

    @PutMapping("/update-info")
    public ResponseEntity<String> updateUserInfo(@AuthenticationPrincipal CustomUserDetailsCustomer userDetails, @RequestBody Customer updatedCustomer) {
        try {
            String username = userDetails.getUsername();
            customerService.updateUserInfo(username, updatedCustomer.getUserName(),
                    updatedCustomer.getNickname(),
                    updatedCustomer.getPhone());
            return ResponseEntity.ok("업데이트에 성공했습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("업데이트에 실패했습니다.");
        }
    }



//
//
//    @GetMapping("/mypage")
//    public String myPage(User user, @AuthenticationPrincipal User currentUser) {
//        List<Category> categoryList = categoryService.findAll();
//
//
//    }
//
//    @PostMapping("/{username}")
//    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
//        User user = userService.findBy(username);
//        if (user != null) {
//            return ResponseEntity.ok(user);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @PutMapping("/{username}")
//    public ResponseEntity<String> updateUserByUsername(@PathVariable String username, @RequestBody User updatedUser) {
//        User existingUser = userService.findByUserName(username);
//        if (existingUser != null) {
//            userService.updateUser(existingUser);
//            existingUser.setUserName(updatedUser.getUserName());
//            existingUser.setEmail(updatedUser.getEmail());
//            existingUser.setPhone(updatedUser.getPhone());
//            existingUser.setNickname(updatedUser.getNickname());
//            return ResponseEntity.ok("유저 업데이트에 성공했습니다.");
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
}
