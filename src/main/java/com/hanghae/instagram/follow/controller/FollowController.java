package com.hanghae.instagram.follow.controller;

import com.hanghae.instagram.common.response.DataResponse;
import com.hanghae.instagram.follow.dto.RequestFollowDto;
import com.hanghae.instagram.follow.dto.ResponseFollowDto;
import com.hanghae.instagram.follow.service.FollowService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.hanghae.instagram.common.response.SuccessCode.FOLLOW_SUCCESS;
import static com.hanghae.instagram.common.response.SuccessCode.UNFOLLOW_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/follow")
public class FollowController {

    private final FollowService followService;

    @PutMapping("")
    public ResponseEntity<DataResponse<ResponseFollowDto>> followAndUnfollow(@RequestBody RequestFollowDto follow){

        boolean newFollowState = followService.doFollowAndUnfollow(follow);

        if(newFollowState) {
            return DataResponse.toResponseEntity(FOLLOW_SUCCESS, new ResponseFollowDto(true));
        } return DataResponse.toResponseEntity(UNFOLLOW_SUCCESS, new ResponseFollowDto(false));
    }
}
