package com.hanghae.instagram.member.controller;

import com.hanghae.instagram.common.exception.CustomException;
import com.hanghae.instagram.common.exception.ErrorCode;
import com.hanghae.instagram.common.response.DataResponse;
import com.hanghae.instagram.common.response.SuccessCode;
import com.hanghae.instagram.common.response.SuccessResponse;
import com.hanghae.instagram.member.dto.RequestLoginMemberDto;
import com.hanghae.instagram.member.dto.RequestSignupMemberDto;
import com.hanghae.instagram.member.dto.ResponseMemberDto;
import com.hanghae.instagram.member.service.MemberService;
import com.hanghae.instagram.security.userdetails.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody RequestLoginMemberDto requestLoginMemberDto, HttpServletResponse response) {
        memberService.login(requestLoginMemberDto, response);
        return SuccessResponse.toResponseEntity(SuccessCode.LOGIN_USER_SUCCESS);
    }

    @GetMapping("getMember")
    public ResponseEntity<?> getMember(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if(userDetails == null){
            throw new CustomException(ErrorCode.MEMBER_NOT_FOUND);
        }
        ResponseMemberDto responseMemberDto = new ResponseMemberDto(userDetails.getProfileImg(), userDetails.getNickname());

        return DataResponse.toResponseEntity(SuccessCode.MEMBER_SUCCESS, responseMemberDto);
    }



}
