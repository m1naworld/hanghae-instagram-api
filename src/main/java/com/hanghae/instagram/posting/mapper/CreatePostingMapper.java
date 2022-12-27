package com.hanghae.instagram.posting.mapper;

import com.hanghae.instagram.common.exception.CustomException;
import com.hanghae.instagram.member.entity.Member;
import com.hanghae.instagram.member.repository.MemberRepository;
import com.hanghae.instagram.posting.dto.createPosting.CreatePostingDto;
import com.hanghae.instagram.posting.dto.createPosting.CreatePostingImgDto;
import com.hanghae.instagram.posting.dto.createPosting.CreatePostingImgMemberTagDto;
import com.hanghae.instagram.posting.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;



import static com.hanghae.instagram.common.exception.ErrorCode.MEMBER_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class CreatePostingMapper {
    private final MemberRepository memberRepository;

    private void memberTagValidator(String nickname) {
        memberRepository.findByNickname(nickname)
                .orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
    }

    public Posting toEntity(CreatePostingDto createPostingDto, Member member) {
        return Posting.builder()
                .contents(createPostingDto.getContents())
                .member(member)
                .build();
    }

    public PostingImg toEntity(CreatePostingImgDto createPostingImgDto, Posting posting) {
        return PostingImg.builder()
                .posting(posting)
                .postingImg(createPostingImgDto.getPostingImg())
                .postingImgNum(createPostingImgDto.getPostingImgNum())
                .build();
    }

    public PostingImgMemberTag toEntity(CreatePostingImgMemberTagDto createPostingImgMemberTagDto,
                                        PostingImg postingImg) {
        memberTagValidator(createPostingImgMemberTagDto.getNickname());

        return PostingImgMemberTag.builder()
                .postingImg(postingImg)
                .memberNickname(createPostingImgMemberTagDto.getNickname())
                .coordinateX(createPostingImgMemberTagDto.getCoordinateX())
                .coordinateY(createPostingImgMemberTagDto.getCoordinateY())
                .build();
    }

    public PostingMemberTag toEntity(String nickname, Posting posting) {
        memberTagValidator(nickname);
        return PostingMemberTag.builder()
                .memberNickname(nickname)
                .posting(posting)
                .build();
    }
}
