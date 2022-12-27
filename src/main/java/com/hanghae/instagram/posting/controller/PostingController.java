package com.hanghae.instagram.posting.controller;

import com.hanghae.instagram.common.response.DataResponse;
import com.hanghae.instagram.common.response.SuccessResponse;
import com.hanghae.instagram.posting.dto.createPosting.RequestCreatePostingDto;
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

import static com.hanghae.instagram.common.response.SuccessCode.CREATE_POSTING_SUCCESS;
import static com.hanghae.instagram.common.response.SuccessCode.SHOW_POSTING_SUCCESS;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posting")
public class PostingController {
    private final PostingService postingService;

    @PostMapping()
    public ResponseEntity<?> createPosting(@RequestBody RequestCreatePostingDto requestCreatePostingDto,
                                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        log.info("UserDetails getEmail: " + userDetails.getEmail());
        postingService.createPosting(requestCreatePostingDto.toCreatePostingDto(), userDetails.getEmail());
        return SuccessResponse.toResponseEntity(CREATE_POSTING_SUCCESS);
    }

    @GetMapping()
    public ResponseEntity<?> showPosting(Pageable pageable,
                                         @AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<ResponseShowPostingDto> data = postingService.showPosting(pageable, userDetails.getNickname());
        return DataResponse.toResponseEntity(SHOW_POSTING_SUCCESS, data);
    }
}
