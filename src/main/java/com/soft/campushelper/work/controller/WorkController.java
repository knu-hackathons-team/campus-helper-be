package com.soft.campushelper.work.controller;

import com.soft.campushelper.global.annotation.Authenticate;
import com.soft.campushelper.global.dto.GlobalResponse;
import com.soft.campushelper.work.controller.dto.WorkRequest;
import com.soft.campushelper.work.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/work")
public class WorkController {

    private final WorkService workService;

    @PostMapping("/{post-id}")
    public GlobalResponse takeWork(
            @Authenticate Long memberId,
            @PathVariable("post-id") Long postId
    ) {
        workService.takeWork(memberId, postId);
        return GlobalResponse.builder().message("수행 시작").build();
    }

    @PostMapping("/{post-id}/done")
    public GlobalResponse doneWork(
            @Authenticate Long memberId,
            @PathVariable("post-id") Long postId,
            @RequestBody WorkRequest.Finish request
    ) {
        workService.completeWork(memberId, postId, request);
        return GlobalResponse.builder().message("수행 완료").build();
    }

    @PostMapping("/{post-id}/rate")
    public GlobalResponse ratingToFinishWork(
            @Authenticate Long memberId,
            @PathVariable("post-id") Long postId,
            @RequestBody WorkRequest.Rate request
    ) {

        workService.ratingToFinishWork(memberId, postId, request);
        return GlobalResponse.builder().message("평가 완료").build();

    }
}
