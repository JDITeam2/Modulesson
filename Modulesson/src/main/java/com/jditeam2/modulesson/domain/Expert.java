package com.jditeam2.modulesson.domain;

import com.jditeam2.modulesson.dto.ExpertFormDto;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "Expert")
public class Expert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Expert_id")
    private Long id;

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

    public static Expert createExpert(ExpertFormDto expertFormDto, PasswordEncoder passwordEncoder) {
        Expert expert = Expert.builder()
                .expertName(expertFormDto.getExpertName())
                .password(expertFormDto.getPassword())
                .nickname(expertFormDto.getNickname())
                .email(expertFormDto.getEmail())
                .phone(expertFormDto.getPhone())
                .introduction(expertFormDto.getIntroduction())
                .build();
        return expert;
    }
}
