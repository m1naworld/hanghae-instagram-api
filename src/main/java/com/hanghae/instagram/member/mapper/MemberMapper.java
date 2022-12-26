package com.hanghae.instagram.member.mapper;

import com.hanghae.instagram.member.dto.RequestSignupMemberDto;
import com.hanghae.instagram.member.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {
    public Member toEntity(RequestSignupMemberDto requestSignupMemberDto) {
        return Member.builder()
                .email(requestSignupMemberDto.getEmail())
                .nickname(requestSignupMemberDto.getNickname())
                .password(requestSignupMemberDto.getPassword())
                .introduce(requestSignupMemberDto.getIntroduce())
                .build();
    }
}
