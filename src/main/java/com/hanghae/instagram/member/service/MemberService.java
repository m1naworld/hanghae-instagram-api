package com.hanghae.instagram.member.service;

import com.hanghae.instagram.common.exception.CustomException;
import com.hanghae.instagram.common.exception.ErrorCode;
import com.hanghae.instagram.security.jwt.JwtUtil;
import com.hanghae.instagram.member.dto.RequestLoginMemberDto;
import com.hanghae.instagram.member.dto.RequestSignupMemberDto;
import com.hanghae.instagram.member.entity.Member;
import com.hanghae.instagram.member.mapper.MemberMapper;
import com.hanghae.instagram.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(RequestSignupMemberDto requestSignupMemberDto) {

        String email = requestSignupMemberDto.getEmail();

        Pattern emailPattern = Pattern.compile("^([\\w\\.\\_\\-])*[a-zA-Z0-9]+([\\w\\.\\_\\-])*([a-zA-Z0-9])+([\\w\\.\\_\\-])+@([a-zA-Z0-9]+\\.)+[a-zA-Z0-9]{2,8}$");
        Pattern pwPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,12}$");
        //최소 6 자, 최소 하나의 문자 및 하나의 숫자 :


        if (!emailPattern.matcher(email).find()) {
            throw new CustomException(ErrorCode.INVALID_EMAIL);
        }

        if (!pwPattern.matcher(requestSignupMemberDto.getPassword()).find()) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }

        Optional<Member> checkUser = memberRepository.findByEmail(email);
        if (checkUser.isPresent()) {
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
        }

        Optional<Member> checkNickname = memberRepository.findByNickname(requestSignupMemberDto.getNickname());
        if (checkNickname.isPresent()) {
            throw new CustomException(ErrorCode.DUPLICATE_NICKNAME);
        }

        if (requestSignupMemberDto.getEmail() == null || requestSignupMemberDto.getNickname() == null || requestSignupMemberDto.getUsername() == null) {
            throw new CustomException(ErrorCode.REQUIRED_ALL);

        }

        String[] arr = new String[]{
                "https://hanghaecatsanddogs.s3.ap-northeast-2.amazonaws.com/profile/%EA%B0%95%EC%95%84%EC%A7%80.jpg",
                "https://hanghaecatsanddogs.s3.ap-northeast-2.amazonaws.com/profile/%EA%B3%B0.jpg",
                "https://hanghaecatsanddogs.s3.ap-northeast-2.amazonaws.com/profile/%EA%B3%B02.jpg",
                "https://hanghaecatsanddogs.s3.ap-northeast-2.amazonaws.com/profile/%EA%BD%83.jpg",
                "https://hanghaecatsanddogs.s3.ap-northeast-2.amazonaws.com/profile/%EA%BD%832.jpg",
                "https://hanghaecatsanddogs.s3.ap-northeast-2.amazonaws.com/profile/%EC%A3%BC%EB%94%94.jpg",
                "https://hanghaecatsanddogs.s3.ap-northeast-2.amazonaws.com/profile/%ED%86%A0%EB%81%BC.jpg",
                "https://hanghaecatsanddogs.s3.ap-northeast-2.amazonaws.com/profile/%EA%B8%B0%EB%B3%B8%EC%9D%B4%EB%AF%B8%EC%A7%80.png"

        };
        SecureRandom sr = new SecureRandom();
        int randomNum = sr.nextInt(8);
        String profileImg = arr[randomNum];

        Member member = memberMapper.toEntity(requestSignupMemberDto, profileImg);
        memberRepository.save(member);
    }

    @Transactional
    public void login(RequestLoginMemberDto requestLoginMemberDto, HttpServletResponse response) {

        String email = requestLoginMemberDto.getEmail();
        String password = requestLoginMemberDto.getPassword();


        Member member = memberRepository.findByEmail(email).orElseThrow(
                () -> new CustomException(ErrorCode.EMAIL_NOT_FOUND)

        );

        if (!passwordEncoder.matches(password, member.getPassword())){
            throw new CustomException(ErrorCode.INCORRECT_PASSWORD);
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(member.getEmail()));
    }

}



