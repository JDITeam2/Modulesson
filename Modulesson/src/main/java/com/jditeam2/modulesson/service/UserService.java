package com.jditeam2.modulesson.service;

import com.jditeam2.modulesson.domain.User;
import com.jditeam2.modulesson.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {
    private final UserRepository userRepository;

    public User saveUser(User user) {
        validateDuplicateUser(user);

        return userRepository.save(user);
    }

    private void validateDuplicateUser(User user) {
        User findUser = userRepository.findByEmail(user.getEmail());
        if (findUser != null) {
            throw new IllegalArgumentException("이미 가입된 회원입니다.");
        }
    }

    public void updateUserInfo(String username, String nickname, String email, String phoneNumber) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            user.setUserName(username);
            user.setNickname(nickname);
            user.setEmail(email);
            user.setPhone(phoneNumber);
            userRepository.save(user);
        }
    }
}
