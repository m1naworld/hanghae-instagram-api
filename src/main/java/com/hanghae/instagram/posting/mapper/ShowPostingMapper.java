package com.hanghae.instagram.posting.mapper;

import com.hanghae.instagram.comment.dto.ResponseComment;
import com.hanghae.instagram.posting.dto.hashtag.ShowPostingByHashTagDto;
import com.hanghae.instagram.posting.dto.showPosting.*;
import com.hanghae.instagram.posting.entity.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
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
            return convertedTime + "전";
        }
        if (day > 0) {
            convertedTime = convertedTime + day + "일 ";
            return convertedTime + "전";
        }
        if (hour > 0) {
            convertedTime = convertedTime + hour + "시간 ";
            return convertedTime + "전";
        }
        if (min > 0) {
            convertedTime = convertedTime + min + "분 ";
            return convertedTime + "전";
        }
        if (sec > 0) {
            convertedTime = convertedTime + sec + "초 ";
            return convertedTime + "전";
        }
        return "0초 전";
    }
    private boolean likeChecked(Posting posting, String nickname) {
        for (int i = 0; i < posting.getPostingLikeList().size(); i++) {
            log.info("Posting Nickname : " + posting.getPostingLikeList().get(i).getNickname());
            log.info("Login Nickname : " + nickname);
            if(posting.getPostingLikeList().get(i).getNickname().equals(nickname)){
                return true;
            }
        }
        return false;
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
                                List<String> hashtagList, List<String> membertagList,
                                String nickname) {
        return ShowPostingDto.builder()
                .id(posting.getId())
                .contents(posting.getContents())
                .likeCount(posting.getLikeCount())
                .nickname(posting.getMember().getNickname())
                .commentCount(posting.getCommentList().size())
                .postingLike(likeChecked(posting, nickname))
                .hashtagList(hashtagList)
                .membertagList(membertagList)
                .createdAt(dateTimeConverter(posting.getCreatedAt()))
                .modifiedAt(dateTimeConverter(posting.getModifiedAt()))
                .imgList(imgList)
                .profileImg(posting.getMember().getProfileImg())
                .build();
    }
    public ShowPostingDetailsDto toDto(Posting posting, List<ShowPostingImgDto> imgList,
                                       List<String> hashtagList, List<String> membertagList,
                                       String nickname, List<ResponseComment> responseCommentList){
        return ShowPostingDetailsDto.builder()
                .id(posting.getId())
                .contents(posting.getContents())
                .likeCount(posting.getLikeCount())
                .nickname(posting.getMember().getNickname())
                .commentCount(posting.getCommentList().size())
                .postingLike(likeChecked(posting, nickname))
                .hashtagList(hashtagList)
                .membertagList(membertagList)
                .createdAt(dateTimeConverter(posting.getCreatedAt()))
                .modifiedAt(dateTimeConverter(posting.getModifiedAt()))
                .imgList(imgList)
                .commentList(responseCommentList)
                .profileImg(posting.getMember().getProfileImg())
                .build();
    }
    public ShowPostingBriefDto toDto(Posting posting, PostingImg postingImg){
        return ShowPostingBriefDto.builder()
                .id(posting.getId())
                .postingImg(postingImg.getPostingImg())
                .likeCount(posting.getLikeCount())
                .commentCount(posting.getCommentList().size())
                .build();
    }

    public ShowPostingByHashTagDto toDto(String hashtag, long postingCount,
                                         List<ShowPostingBriefDto> showPostingBriefDtoList){
        return ShowPostingByHashTagDto.builder()
                .hashtag(hashtag)
                .postingCount(postingCount)
                .showPostingBriefDtoList(showPostingBriefDtoList)
                .build();
    }
}
