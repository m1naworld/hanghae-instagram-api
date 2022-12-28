package com.hanghae.instagram.member.mapper;

import com.hanghae.instagram.member.dto.RequestSignupMemberDto;
import com.hanghae.instagram.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberMapper {

    private final PasswordEncoder passwordEncoder;

    public Member toEntity(RequestSignupMemberDto requestSignupMemberDto, String profileImg) {

        String encryptedPassword = passwordEncoder.encode(requestSignupMemberDto.getPassword());

        return Member.builder()
                .email(requestSignupMemberDto.getEmail())
                .nickname(requestSignupMemberDto.getNickname())
                .password(encryptedPassword)
                .username(requestSignupMemberDto.getUsername())
                .profileImg(profileImg)
                .build();
    }
}
