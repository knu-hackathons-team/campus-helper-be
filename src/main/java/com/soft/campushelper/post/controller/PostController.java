package com.soft.campushelper.post.controller;

import com.soft.campushelper.global.annotation.Authenticate;
import com.soft.campushelper.global.dto.GlobalResponse;
import com.soft.campushelper.global.dto.PagingResponse;
import com.soft.campushelper.post.controller.dto.PostRequest;
import com.soft.campushelper.post.controller.dto.PostResponse;
import com.soft.campushelper.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    @PostMapping
    public GlobalResponse addPost(
            @Authenticate Long memberId,
            @RequestBody PostRequest.Add request
    ){
        postService.addPost(memberId, request);
        return GlobalResponse.builder().message("게시글 등록이 완료되었습니다.").build();
    }

    @GetMapping
    public PagingResponse<PostResponse.Info> getPostList(
            @Authenticate Long memberId,
            @PageableDefault(page = 0, size = 10) Pageable pageable
    ){
        Page<PostResponse.Info> postList = postService.getPostList(memberId, pageable);

        return PagingResponse.from(postList);
    }
}
