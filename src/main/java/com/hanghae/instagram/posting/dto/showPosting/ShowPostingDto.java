package com.hanghae.instagram.posting.dto.showPosting;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ShowPostingDto {
    private long id;
    private String contents;
    private long likeCount;
    private String nickname;
    private long commentCount;
    private boolean postingLike;
    private List<String> hashtagList;
    private List<String> membertagList;
    private String createdAt;
    private String modifiedAt;
    private List<ShowPostingImgDto> imgList;

    public ResponseShowPostingDto toResponse(){
        // 빌더 패턴을 이용할 때, List에 대한 DeepCopy가 필요. 일단 바쁘니 리팩토링 때 적용
        return ResponseShowPostingDto.builder()
                .id(id)
                .contents(contents)
                .likeCount(likeCount)
                .nickname(nickname)
                .commentCount(commentCount)
                .postingLike(postingLike)
                .hashtagList(hashtagList)
                .membertagList(membertagList)
                .createdAt(createdAt)
                .modifiedAt(modifiedAt)
                .imgList(imgList)
                .build();
    }
}
