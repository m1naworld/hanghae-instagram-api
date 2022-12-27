package com.hanghae.instagram.posting.controller;

import com.hanghae.instagram.common.response.DataResponse;
import com.hanghae.instagram.common.response.SuccessResponse;
import com.hanghae.instagram.posting.dto.createPosting.RequestCreatePostingDto;
import com.hanghae.instagram.posting.dto.showPosting.ResponseShowPostingDto;
import com.hanghae.instagram.posting.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hanghae.instagram.common.response.SuccessCode.CREATE_POSTING_SUCCESS;
import static com.hanghae.instagram.common.response.SuccessCode.SHOW_POSTING_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posting")
public class PostingController {
    private final PostingService postingService;

    @PostMapping()
    public ResponseEntity<?> createPosting(@RequestBody RequestCreatePostingDto requestCreatePostingDto) {
        postingService.createPosting(requestCreatePostingDto.toCreatePostingDto(), "user1@test");
        return SuccessResponse.toResponseEntity(CREATE_POSTING_SUCCESS);
    }

    @GetMapping()
    public ResponseEntity<?> showPosting(Pageable pageable) {
        List<ResponseShowPostingDto> data = postingService.showPosting(pageable);
        return DataResponse.toResponseEntity(SHOW_POSTING_SUCCESS, data);
    }


}
