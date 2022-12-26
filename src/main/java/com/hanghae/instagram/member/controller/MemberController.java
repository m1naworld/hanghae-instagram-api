package com.hanghae.instagram.member.controller;

import com.hanghae.instagram.common.response.SuccessCode;
import com.hanghae.instagram.common.response.SuccessResponse;
import com.hanghae.instagram.member.dto.RequestSignupMemberDto;
import com.hanghae.instagram.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody RequestSignupMemberDto requestSignupMemberDto) {
        memberService.signUp(requestSignupMemberDto);
        return SuccessResponse.toResponseEntity(SuccessCode.SIGNUP_USER_SUCCESS);
    }
}
