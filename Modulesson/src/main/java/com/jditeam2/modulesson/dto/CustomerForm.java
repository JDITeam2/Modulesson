package com.jditeam2.modulesson.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
public class CustomerForm {
    @NotEmpty(message = "이름을 입력해 주세요.")
    @Size(min = 3)
    private String userName;

    @NotEmpty(message = "비밀번호를 입력해 주세요.")
    @Size(min = 6, max = 16, message = "비밀번호는 4자 이상, 16자 이하로 입력해 주세요.")
    private String password;

    @NotEmpty(message = "닉네임을 입력해 주세요.")
    @Size(max = 9, message = "닉네임은 9자를 넘길 수 없습니다.")
    private String nickname;

    @NotEmpty(message = "이메일을 입력해 주세요.")
    @Email(message = "이메일 형식으로 입력해 주세요.")
    private String email;

    @NotEmpty(message = "전화번호를 입력해 주세요.")
    @Size(min = 13, max = 13, message = "전화번호는 - 를 제외 하고 입력해 주세요")
    private String phone;

    @Builder
    public CustomerForm(Long id, String userName, String password, String nickname, String email, String phone) {
        this.userName = userName;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.phone = phone;
    }
}
