package com.jditeam2.modulesson.domain.detail;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomUserDetailsExpert extends CustomUserDetails {

        private String additionalExpertInfo;  // Expert 특유의 추가 정보

    public CustomUserDetailsExpert(String username, String password, Collection<? extends GrantedAuthority> authorities, String additionalExpertInfo) {
            super(username, password, authorities);
            this.additionalExpertInfo = additionalExpertInfo;
        }

        // Expert 특유의 추가 메서드나 필드를 추가할 수 있음

        public String getExpertField() {
            return additionalExpertInfo;
        }
}
