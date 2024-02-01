package com.jditeam2.modulesson.domain;

import com.jditeam2.modulesson.dto.UserFormDto;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_id")
    private Long id;

    @Column(unique = true)
    private String email;
    private String userName;
    private String password;
    private String nickname;
    private String phone;

    @Builder
    public User(String userName, String password, String nickname, String email, String phone) {
        this.userName = userName;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.phone = phone;
    }

    public static User createUser(UserFormDto userFormDto, PasswordEncoder passwordEncoder) {
        User user = User.builder()
                .userName(userFormDto.getUserName())
                .password(userFormDto.getPassword())
                .nickname(userFormDto.getNickname())
                .email(userFormDto.getEmail())
                .phone(userFormDto.getPhone())
                .build();
        return user;
    }
}
