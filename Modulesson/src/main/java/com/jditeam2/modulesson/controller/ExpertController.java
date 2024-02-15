package com.jditeam2.modulesson.controller;

import com.jditeam2.modulesson.domain.Expert;
import com.jditeam2.modulesson.domain.detail.CustomUserDetailsExpert;
import com.jditeam2.modulesson.dto.ExpertForm;
import com.jditeam2.modulesson.service.ExpertService;
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
@RequestMapping("/api/expert")
public class ExpertController {

    @Autowired
    private final ExpertService expertService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/new")
    public String expertForm(Model model) {
        model.addAttribute("expertFormDto", new ExpertForm());
        return "expert/expertForm";
    }

    @PostMapping(value = "newExpert")
    public  String expertForm(@Valid ExpertForm expertForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "expert/expertForm";
        }
        try {
            Expert expert = Expert.createExpert(expertForm, passwordEncoder);
            expertService.saveExpert(expert);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "expert/expertForm";
        }
        return "redirect:/";
    }

    @GetMapping(value = "/login")
    public String loginCustomer() {
        return "/expert/customerLoginForm";
    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
        return "/expert/customerLoginForm";
    }

    @PutMapping("/update-info")
    public ResponseEntity<String> updateUserInfo(@AuthenticationPrincipal CustomUserDetailsExpert userDetails, @RequestBody Expert updatedExpert) {
        try {
            String username = userDetails.getUsername();
            expertService.updateExpertInfo(
                    updatedExpert.getExpertName(),
                    updatedExpert.getNickname(),
                    updatedExpert.getEmail(),
                    updatedExpert.getEmail(),
                    updatedExpert.getIntroduction());
            return ResponseEntity.ok("업데이트에 성공했습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("업데이트에 실패했습니다.");
        }
    }


}
