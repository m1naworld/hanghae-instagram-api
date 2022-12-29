package com.hanghae.instagram.posting.dto.hashtag;

import com.hanghae.instagram.posting.dto.showPosting.ShowPostingBriefDto;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ResponseShowPostingByHashTagDto {
    private String hashtag;
    private long postingCount;
    List<ShowPostingBriefDto> postingBriefList = new ArrayList<>();

    @Builder
    public ResponseShowPostingByHashTagDto(String hashtag, long postingCount, List<ShowPostingBriefDto> postingBriefList) {
        this.hashtag = hashtag;
        this.postingCount = postingCount;

        for (ShowPostingBriefDto showPostingBriefDto : postingBriefList) {
            this.postingBriefList.add(showPostingBriefDto);
        }
    }
}
