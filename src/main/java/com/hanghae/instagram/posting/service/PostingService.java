package com.hanghae.instagram.posting.service;

import com.hanghae.instagram.comment.dto.ResponseComment;
import com.hanghae.instagram.common.exception.CustomException;
import com.hanghae.instagram.member.entity.Member;
import com.hanghae.instagram.member.repository.MemberRepository;
import com.hanghae.instagram.posting.dto.createPosting.CreatePostingDto;
import com.hanghae.instagram.posting.dto.showPosting.*;
import com.hanghae.instagram.posting.entity.*;
import com.hanghae.instagram.posting.mapper.CreatePostingMapper;
import com.hanghae.instagram.posting.mapper.ShowPostingMapper;
import com.hanghae.instagram.posting.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.hanghae.instagram.common.exception.ErrorCode.MEMBER_NOT_FOUND;
import static com.hanghae.instagram.common.exception.ErrorCode.FORUM_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostingService {
    private final MemberRepository memberRepository;
    private final PostingRepository postingRepository;
    private final PostingHashTagRepository postingHashTagRepository;
    private final PostingMemberTagRepository postingMemberTagRepository;
    private final PostingImgRepository postingImgRepository;
    private final PostingImgMemberTagRepository postingImgMemberTagRepository;

    private final CreatePostingMapper createPostingMapper;
    private final ShowPostingMapper showPostingMapper;

    @Transactional
    public void createPosting(CreatePostingDto createPostingDto, String email) {
        // 1. email을 통해 로그인한 사용자의 정보 가져오기
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
        // 2. 게시글 Entity 생성 및 저장
        Posting posting = createPostingMapper.toEntity(createPostingDto, member);
        postingRepository.save(posting);
        // 3. 게시글 이미지 Entity 생성 및 저장
        for (int i = 0; i < createPostingDto.getImgList().size(); i++) {
            PostingImg postingImg = createPostingMapper.toEntity(createPostingDto.getImgList().get(i), posting);
            postingImgRepository.save(postingImg);

            // 4. 이미지 회원태그 Entity 생성 및 저장
            for (int j = 0; j < createPostingDto.getImgList().get(i).getMemberTagList().size(); j++) {
                PostingImgMemberTag postingImgMemberTag = createPostingMapper.toEntity(
                        createPostingDto.getImgList().get(i).getMemberTagList().get(j),
                        postingImg
                );
                postingImgMemberTagRepository.save(postingImgMemberTag);
            }
        }
        // 5. 게시글 회원태그 Entity 생성
        for (int i = 0; i < createPostingDto.getMembertagList().size(); i++) {
            PostingMemberTag postingMemberTag = createPostingMapper.toEntity(
                    createPostingDto.getMembertagList().get(i),
                    posting
            );
            postingMemberTagRepository.save(postingMemberTag);
        }
        // 6. 게시글 해쉬태그 Entity 생성
        for (int i = 0; i < createPostingDto.getHashtagList().size(); i++) {
            PostingHashTag postingHashTag = new PostingHashTag(
                    createPostingDto.getHashtagList().get(i),
                    posting
            );
            postingHashTagRepository.save(postingHashTag);
        }
    }

    @Transactional(readOnly = true)
    public List<ResponseShowPostingDto> showPosting(Pageable pageable, String nickname){
        List<ResponseShowPostingDto> responseShowPostingDtoList = new ArrayList<>();

        List<Posting> postingList = postingRepository.findAllByOrderByLikeCountDesc(pageable);
        List<ShowPostingDto> showPostingDtoList = new ArrayList<>();
        for (Posting posting : postingList) {
            // 1. HashTag 정보 가져오기
            List<PostingHashTag> postingHashTagList
                    = postingHashTagRepository.findAllByPostingId(posting.getId());
            List<String> hashtagList = new ArrayList<>();
            for (PostingHashTag postingHashTag : postingHashTagList) {
                hashtagList.add(postingHashTag.getHashtag());
            }

            // 2. MemberTag 정보 가져오기
            List<PostingMemberTag> postingMemberTagList
                    = postingMemberTagRepository.findAllByPostingId(posting.getId());
            List<String> membertagList = new ArrayList<>();
            for (PostingMemberTag postingMemberTag : postingMemberTagList) {
                membertagList.add(postingMemberTag.getMemberNickname());
            }

            // 3. PostingImg 정보 가져오기
            List<PostingImg> postingImgList = postingImgRepository.findAllByPostingId(posting.getId());
            List<ShowPostingImgDto> imgList = new ArrayList<>();
            for (PostingImg postingImg : postingImgList) {
                List<PostingImgMemberTag> postingImgMemberTagList
                        = postingImgMemberTagRepository.findAllByPostingImgId(postingImg.getId());
                List<ShowPostingImgMemberTagDto> showPostingImgMemberTagDtoList = new ArrayList<>();
                for (PostingImgMemberTag postingImgMemberTag : postingImgMemberTagList) {
                    showPostingImgMemberTagDtoList.add(
                            showPostingMapper.toDto(postingImgMemberTag)
                    );
                }
                imgList.add(showPostingMapper.toDto(postingImg, showPostingImgMemberTagDtoList));
            }

            // 4. ShowPostingDto 형식으로 변환하고 리스트에 추가하기
            showPostingDtoList.add(showPostingMapper.toDto(posting, imgList, hashtagList, membertagList, nickname));
        }

        for (ShowPostingDto showPostingDto : showPostingDtoList) {
            responseShowPostingDtoList.add(showPostingDto.toResponse());
        }

        return responseShowPostingDtoList;
    }

    @Transactional(readOnly = true)
    public ResponseShowPostingDetailsDto showPostingDetails (long postingId, String nickname){
        Posting posting = postingRepository.findById(postingId)
                .orElseThrow(()->new CustomException(FORUM_NOT_FOUND));

        // 1. HashTag 정보 가져오기
        List<PostingHashTag> postingHashTagList
                = postingHashTagRepository.findAllByPostingId(posting.getId());
        List<String> hashtagList = new ArrayList<>();
        for (PostingHashTag postingHashTag : postingHashTagList) {
            hashtagList.add(postingHashTag.getHashtag());
        }

        // 2. MemberTag 정보 가져오기
        List<PostingMemberTag> postingMemberTagList
                = postingMemberTagRepository.findAllByPostingId(posting.getId());
        List<String> membertagList = new ArrayList<>();
        for (PostingMemberTag postingMemberTag : postingMemberTagList) {
            membertagList.add(postingMemberTag.getMemberNickname());
        }

        // 3. PostingImg 정보 가져오기
        List<PostingImg> postingImgList = postingImgRepository.findAllByPostingId(posting.getId());
        List<ShowPostingImgDto> imgList = new ArrayList<>();
        for (PostingImg postingImg : postingImgList) {
            List<PostingImgMemberTag> postingImgMemberTagList
                    = postingImgMemberTagRepository.findAllByPostingImgId(postingImg.getId());
            List<ShowPostingImgMemberTagDto> showPostingImgMemberTagDtoList = new ArrayList<>();
            for (PostingImgMemberTag postingImgMemberTag : postingImgMemberTagList) {
                showPostingImgMemberTagDtoList.add(
                        showPostingMapper.toDto(postingImgMemberTag)
                );
            }
            imgList.add(showPostingMapper.toDto(postingImg, showPostingImgMemberTagDtoList));
        }

        // 4. Comment 정보 가져오기
        List<ResponseComment> responseCommentList = new ArrayList<>();
        for (int i = 0; i < posting.getCommentList().size(); i++) {
            responseCommentList.add(new ResponseComment(posting.getId(), posting.getCommentList().get(i)));
        }

        return showPostingMapper
                .toDto(posting, imgList, hashtagList, membertagList, nickname, responseCommentList)
                .toResponse();
    }

}
