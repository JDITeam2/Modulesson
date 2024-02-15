package com.jditeam2.modulesson.service;

import com.jditeam2.modulesson.domain.Customer;
import com.jditeam2.modulesson.domain.Expert;
import com.jditeam2.modulesson.repository.ExpertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ExpertService implements UserDetailsService {
    private final ExpertRepository expertRepository;

    public Expert saveExpert(Expert expert) {
        validateDuplicateExpert(expert);

        return expertRepository.save(expert);
    }

    private void validateDuplicateExpert(Expert expert) {
        Expert findExpert = expertRepository.findByEmail(expert.getEmail());
        if (findExpert != null) {
            throw new IllegalArgumentException("이미 가입된 회원입니다.");
        }
    }

    public void updateExpertInfo(String expertName, String nickname, String email, String phone, String introduction) {
        Expert expert = expertRepository.findByEmail(email);
        if (expert != null) {
            expert.setExpertName(expertName);
            expert.setNickname(nickname);
            expert.setEmail(email);
            expert.setPhone(phone);
            expert.setIntroduction(introduction);
            expertRepository.save(expert);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Expert expert = expertRepository.findByEmail(email);

        if (expert == null) {
            throw new UsernameNotFoundException(email);
        }

        return User
                .builder()
                .username(expert.getEmail())
                .password(expert.getPassword())
                .build();
    }
}
