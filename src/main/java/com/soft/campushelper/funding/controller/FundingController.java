package com.soft.campushelper.funding.controller;

import com.soft.campushelper.funding.service.FundingService;
import com.soft.campushelper.global.annotation.Authenticate;
import com.soft.campushelper.global.dto.GlobalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/funding")
public class FundingController {
    private final FundingService fundingService;

    @PostMapping("/post/{post-id}/participate")
    public GlobalResponse participateInPost(
            @Authenticate Long memberId,
            @PathVariable("post-id") Long postId
    ) {
        fundingService.participateInPost(memberId, postId);
        return GlobalResponse.builder().message("펀딩 참여가 완료되었습니다.").build();
    }

    @DeleteMapping("/post/{post-id}")
    public GlobalResponse deletePost(
            @Authenticate Long memberId,
            @PathVariable("post-id") Long postId
    ){
        fundingService.cancelFunding(memberId, postId);
        return GlobalResponse.builder().message("펀딩 취소 완료").build();
    }
}
