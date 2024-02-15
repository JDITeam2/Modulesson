package com.jditeam2.modulesson.domain;

import com.jditeam2.modulesson.dto.ExpertForm;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@ToString
@Getter
@Setter
@Table(name = "expert")
public class Expert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Expert_id")
    private Long id;

    @Column(unique = true)
    private String email;
    private String expertName;
    private String password;
    private String nickname;
    private String phone;
    private String introduction;

    private String certification;

    @Builder
    public Expert(String expertName, String password, String nickname, String email, String phone, String introduction) {
        this.expertName = expertName;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.phone = phone;
        this.introduction = introduction;
    }

    public static Expert createExpert(ExpertForm expertForm, PasswordEncoder passwordEncoder) {
        Expert expert = Expert.builder()
                .expertName(expertForm.getExpertName())
                .password(passwordEncoder.encode(expertForm.getPassword()))
                .nickname(expertForm.getNickname())
                .email(expertForm.getEmail())
                .phone(expertForm.getPhone())
                .introduction(expertForm.getIntroduction())
                .build();
        return expert;
    }
}
