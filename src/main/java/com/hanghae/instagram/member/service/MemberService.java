package com.hanghae.instagram.member.service;

import com.hanghae.instagram.common.exception.CustomException;
import com.hanghae.instagram.common.exception.ErrorCode;
import com.hanghae.instagram.jwt.JwtUtil;
import com.hanghae.instagram.member.dto.RequestLoginMemberDto;
import com.hanghae.instagram.member.dto.RequestSignupMemberDto;
import com.hanghae.instagram.member.entity.Member;
import com.hanghae.instagram.member.mapper.MemberMapper;
import com.hanghae.instagram.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final JwtUtil jwtUtil;

    @Transactional
    public void signUp(RequestSignupMemberDto requestSignupMemberDto) {


        String email = requestSignupMemberDto.getEmail();

        Pattern emailPattern = Pattern.compile("^([\\w\\.\\_\\-])*[a-zA-Z0-9]+([\\w\\.\\_\\-])*([a-zA-Z0-9])+([\\w\\.\\_\\-])+@([a-zA-Z0-9]+\\.)+[a-zA-Z0-9]{2,8}$");
        Pattern pwPattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,12}$");
        //최소 8자 이상 12이하로 영문자 대문자, 영문자 소문자, 숫자, 특수문자가 각각 최소 1개 이상


        if(!emailPattern.matcher(email).find()){
            throw new CustomException(ErrorCode.INVALID_EMAIL);
        }

        if(!pwPattern.matcher(requestSignupMemberDto.getPassword()).find()){
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

        if(requestSignupMemberDto.getEmail()==null || requestSignupMemberDto.getNickname()==null){
            throw new CustomException(ErrorCode.REQUIRED_ALL);

        }

        Member member = memberMapper.toEntity(requestSignupMemberDto);
        memberRepository.save(member);
    }

    @Transactional
    public void login(RequestLoginMemberDto requestLoginMemberDto, HttpServletResponse response) {

            String email = requestLoginMemberDto.getEmail();
            String password = requestLoginMemberDto.getPassword();

            Member member = memberRepository.findByEmail(email).orElseThrow(
                    ()-> new CustomException(ErrorCode.EMAIL_NOT_FOUND)

            );

            if (!member.getPassword().equals(password)){
                throw new CustomException(ErrorCode.INCORRECT_PASSWORD);
            }

            response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(member.getEmail()));
        }

        }



