package com.hanghae.instagram.comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RequestComment {
    private String comment;

    private String nickname;

}
