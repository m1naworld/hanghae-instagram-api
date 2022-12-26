package com.hanghae.instagram.posting.mapper;

import com.hanghae.instagram.posting.dto.showPosting.ShowPostingDto;
import com.hanghae.instagram.posting.dto.showPosting.ShowPostingImgDto;
import com.hanghae.instagram.posting.dto.showPosting.ShowPostingImgMemberTagDto;
import com.hanghae.instagram.posting.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ShowPostingMapper {
    private String dateTimeConverter(LocalDateTime time) {
        String convertedTime = "";
        Duration duration = Duration.between(time, LocalDateTime.now());
        long differ = duration.getSeconds();
        long sec = differ % 60;
        long min = (differ / 60) % 60;
        long hour = (differ / (60 * 60)) % 60;
        long day = (differ / (60 * 60 * 60)) % 24;
        long year = (differ / (60 * 60 * 60 * 24)) % 365;
        if (year > 0) {
            convertedTime = convertedTime + year + "년 ";
        }
        if (day > 0) {
            convertedTime = convertedTime + day + "일 ";
        }
        if (hour > 0) {
            convertedTime = convertedTime + hour + "시간 ";
        }
        if (min > 0) {
            convertedTime = convertedTime + min + "분 ";
        }
        if (sec > 0) {
            convertedTime = convertedTime + sec + "초 전";
        }
        return convertedTime;
    }

    public ShowPostingImgMemberTagDto toDto(PostingImgMemberTag postingImgMemberTag) {
        return ShowPostingImgMemberTagDto.builder()
                .nickname(postingImgMemberTag.getMemberNickname())
                .coordinateX(postingImgMemberTag.getCoordinateX())
                .coordinateY(postingImgMemberTag.getCoordinateY())
                .build();
    }
    public ShowPostingImgDto toDto(PostingImg postingImg, List<ShowPostingImgMemberTagDto> memberTagList) {
        return ShowPostingImgDto.builder()
                .postingImg(postingImg.getPostingImg())
                .postingImgNum(postingImg.getPostingImgNum())
                .memberTagList(memberTagList)
                .build();
    }
    public ShowPostingDto toDto(Posting posting, List<ShowPostingImgDto> imgList,
                                List<String> hashtagList, List<String> membertagList) {
        return ShowPostingDto.builder()
                .id(posting.getId())
                .contents(posting.getContents())
                .likeCount(posting.getLikeCount())
                .nickname(posting.getMember().getNickname())
                .commentCount(11) // Comment 기능 구현 이후 로직 수정 필요
                .postingLike(false) // Like 기능 구현 이후 로직 수정 필요
                .hashtagList(hashtagList)
                .membertagList(membertagList)
                .createdAt(dateTimeConverter(posting.getCreatedAt()))
                .modifiedAt(dateTimeConverter(posting.getModifiedAt()))
                .imgList(imgList)
                .build();
    }
}
