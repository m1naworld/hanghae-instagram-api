package com.hanghae.instagram.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KakaoMemberInfoDto {
    private Long id;
    private String email;
    private String nickname;
    private String profileImg;


}
