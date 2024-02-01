package com.jditeam2.modulesson.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
public class ExpertFormDto {
    @NotEmpty(message = "이름을 입력해 주세요.")
    @Size(min = 3)
    private String expertName;

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

    @Size(max = 1000, message = "소개글은 1000자 이내 입니다.")
    private String introduction;

    @Builder
    public ExpertFormDto(Long id, String expertName, String password, String nickname, String email, String phone, String introduction) {
        this.expertName = expertName;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.phone = phone;
        this.introduction = introduction;
    }

}
