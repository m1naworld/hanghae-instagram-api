package com.hanghae.instagram.member.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class RequestSignupMemberDto {

    private String email;

    private String password;

    private String nickname;

    private String introduce;


}
