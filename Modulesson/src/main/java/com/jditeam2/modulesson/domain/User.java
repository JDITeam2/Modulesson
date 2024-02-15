package com.jditeam2.modulesson.domain;

import com.jditeam2.modulesson.dto.UserForm;
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

    public static User createUser(UserForm userForm, PasswordEncoder passwordEncoder) {
        User user = User.builder()
                .userName(userForm.getUserName())
                .password(userForm.getPassword())
                .nickname(userForm.getNickname())
                .email(userForm.getEmail())
                .phone(userForm.getPhone())
                .build();
        return user;
    }
}
