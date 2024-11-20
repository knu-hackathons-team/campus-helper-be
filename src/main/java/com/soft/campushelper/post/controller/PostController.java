package com.soft.campushelper.post.controller;

import com.soft.campushelper.global.annotation.Authenticate;
import com.soft.campushelper.global.dto.GlobalResponse;
import com.soft.campushelper.global.dto.PagingResponse;
import com.soft.campushelper.post.controller.dto.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
    @PostMapping
    public GlobalResponse addPost(
            @Authenticate Long memberId
    ){
        return GlobalResponse.builder().message("게시글 등록이 완료되었습니다.").build();
    }

    @GetMapping
    public PagingResponse<PostResponse.Info> getPostList(
            @Authenticate Long memberId
    ){
        return null;
    }
}
