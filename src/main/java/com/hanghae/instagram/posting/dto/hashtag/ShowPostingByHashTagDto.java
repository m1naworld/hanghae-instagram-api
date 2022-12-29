package com.hanghae.instagram.posting.dto.hashtag;

import com.hanghae.instagram.posting.dto.showPosting.ShowPostingBriefDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ShowPostingByHashTagDto {
    private String hashtag;
    private long postingCount;
    List<ShowPostingBriefDto> showPostingBriefDtoList;

    public ResponseShowPostingByHashTagDto toEntity() {
        return ResponseShowPostingByHashTagDto.builder()
                .hashtag(hashtag)
                .postingCount(postingCount)
                .postingBriefList(showPostingBriefDtoList)
                .build();
    }
}
