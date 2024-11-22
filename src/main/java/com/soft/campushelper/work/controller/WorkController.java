package com.soft.campushelper.work.controller;

import com.soft.campushelper.global.annotation.Authenticate;
import com.soft.campushelper.global.dto.GlobalResponse;
import com.soft.campushelper.work.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/work")
public class WorkController {

    private final WorkService workService;

    @PostMapping("/{post-id}")
    public GlobalResponse takeWork(
        @Authenticate Long memberId,
        @PathVariable("post-id") Long postId
    ){
        workService.takeWork(memberId, postId);
        return GlobalResponse.builder().message("수행 시작").build();
    }

    @PostMapping("/{post-id}/done")
    public GlobalResponse doneWork(
            @Authenticate Long memberId,
            @PathVariable("post-id") Long postId
    ){

    }
}
