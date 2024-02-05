package com.jditeam2.modulesson.controller;

import com.jditeam2.modulesson.domain.User;
import com.jditeam2.modulesson.domain.detail.CustomUserDetailsUser;
import com.jditeam2.modulesson.dto.UserForm;
import com.jditeam2.modulesson.service.UserService;
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
public class UserController {

    @Autowired
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/new")
    public String userForm(Model model) {
        model.addAttribute("userFormDto", new UserForm());
        return "user/userForm";
    }

    @PostMapping(value = "newUser")
    public String userForm(@Valid UserForm userForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "user/userForm";
        }

        try {
            User user = User.createUser(userForm, passwordEncoder);
            userService.saveUser(user);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "user/userForm";
        }

        return "redirect:/";
    }


    @PutMapping("/update-info")
    public ResponseEntity<String> updateUserInfo(@AuthenticationPrincipal CustomUserDetailsUser userDetails, @RequestBody User updatedUser) {
        try {
            String username = userDetails.getUsername();
            userService.updateUserInfo(username, updatedUser.getUserName(),
                    updatedUser.getNickname(),
                    updatedUser.getPhone());
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
