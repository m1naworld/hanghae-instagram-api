package com.hanghae.instagram.posting.controller;

import com.hanghae.instagram.common.response.DataResponse;
import com.hanghae.instagram.common.response.SuccessResponse;
import com.hanghae.instagram.posting.dto.createPosting.RequestCreatePostingDto;
import com.hanghae.instagram.posting.dto.hashtag.ResponseShowPostingByHashTagDto;
import com.hanghae.instagram.posting.dto.showPosting.ResponseShowPostingDetailsDto;
import com.hanghae.instagram.posting.dto.showPosting.ResponseShowPostingDto;
import com.hanghae.instagram.posting.service.PostingService;
import com.hanghae.instagram.security.userdetails.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hanghae.instagram.common.response.SuccessCode.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posting")
public class PostingController {
    private final PostingService postingService;

    @PostMapping()
    public ResponseEntity<?> createPosting(@RequestBody RequestCreatePostingDto requestCreatePostingDto,
                                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        ResponseShowPostingDto data = postingService.createPosting(requestCreatePostingDto.toCreatePostingDto(), userDetails.getEmail());
        return DataResponse.toResponseEntity(CREATE_POSTING_SUCCESS, data);
    }

    @GetMapping()
    public ResponseEntity<?> showPosting(Pageable pageable,
                                         @AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<ResponseShowPostingDto> data = postingService.showPosting(pageable, userDetails.getNickname());
        return DataResponse.toResponseEntity(SHOW_POSTING_SUCCESS, data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> showPostingDetails(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                         @PathVariable long id) {
        ResponseShowPostingDetailsDto data = postingService.showPostingDetails(id, userDetails.getNickname());
        return DataResponse.toResponseEntity(SHOW_POSTING_DETAIL_SUCCESS, data);
    }

    @GetMapping("/hashtag/{hashtag}")
    public ResponseEntity<?> showPostingByHashtag(Pageable pageable,
                                                  @PathVariable String hashtag){
        ResponseShowPostingByHashTagDto data
                = postingService.showPostingByHashTag(pageable, hashtag);
        return DataResponse.toResponseEntity(SHOW_POSTING_BY_HASHTAG_SUCCESS, data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePosting(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                           @PathVariable long id) {
        postingService.deletePosting(id, userDetails.getNickname());
        return SuccessResponse.toResponseEntity(DELETE_POSTING_SUCCESS);
    }
}
