package com.hanghae.instagram.member.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hanghae.instagram.common.exception.CustomException;
import com.hanghae.instagram.common.exception.ErrorCode;
import com.hanghae.instagram.common.response.DataResponse;
import com.hanghae.instagram.common.response.SuccessCode;
import com.hanghae.instagram.common.response.SuccessResponse;
import com.hanghae.instagram.member.dto.RequestLoginMemberDto;
import com.hanghae.instagram.member.dto.RequestSignupMemberDto;
import com.hanghae.instagram.member.dto.ResponseMemberDto;
import com.hanghae.instagram.member.entity.Member;
import com.hanghae.instagram.member.service.KakaoService;
import com.hanghae.instagram.member.service.MemberService;
import com.hanghae.instagram.security.jwt.JwtUtil;
import com.hanghae.instagram.security.userdetails.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    private final KakaoService kakaoService;


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

    @GetMapping("/info")
    public ResponseEntity<?> getMember(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if(userDetails == null){
            throw new CustomException(ErrorCode.MEMBER_NOT_FOUND);
        }

        Member member = userDetails.getMember();
        ResponseMemberDto responseMemberDto = new ResponseMemberDto(member.getProfileImg(), member.getNickname(), member.getFollowerCount(), member.getFollowingCount());

        return DataResponse.toResponseEntity(SuccessCode.MEMBER_SUCCESS, responseMemberDto);
    }


    @GetMapping("/kakao/callback")
    public ResponseEntity<?> kakaoLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
        // code: 카카오 서버로부터 받은 인가 코드
        kakaoService.kakaoLogin(code, response);
        return SuccessResponse.toResponseEntity(SuccessCode.LOGIN_KAKAO_SUCCESS);
    }



}
