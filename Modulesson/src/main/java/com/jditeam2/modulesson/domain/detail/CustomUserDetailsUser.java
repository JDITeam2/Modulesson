package com.jditeam2.modulesson.domain.detail;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomUserDetailsUser extends CustomUserDetails {

        private String additionalUserInfo;  // User 특유의 추가 정보

        public CustomUserDetailsUser(String username, String password, Collection<? extends GrantedAuthority> authorities, String additionalUserInfo) {
            super(username, password, authorities);
            this.additionalUserInfo = additionalUserInfo;
        }

        // User 특유의 추가 메서드나 필드를 추가할 수 있음

        public String getAdditionalUserInfo() {
            return additionalUserInfo;
        }
}
