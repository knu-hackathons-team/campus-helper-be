package com.soft.campushelper.post.controller;

import com.soft.campushelper.global.annotation.Authenticate;
import com.soft.campushelper.global.dto.GlobalResponse;
import com.soft.campushelper.global.dto.PagingResponse;
import com.soft.campushelper.post.controller.dto.PostRequest;
import com.soft.campushelper.post.controller.dto.PostResponse;
import com.soft.campushelper.post.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    /**
     * 게시글 등록
     */
    @PostMapping
    public GlobalResponse addPost(
            @Authenticate Long memberId,
            @RequestBody PostRequest.Add request
    ){
        postService.addPost(memberId, request);
        return GlobalResponse.builder().message("게시글 등록이 완료되었습니다.").build();
    }

    /**
     * 게시글 리스트 반환
     */
    @GetMapping
    public PagingResponse<PostResponse.Info> getPostList(
            @PageableDefault(page = 0, size = 10) Pageable pageable,
            HttpServletRequest request
    ){
        String memberId = (String) request.getAttribute("memberId");
        if (memberId != null) {
            // 로그인 사용자
            return PagingResponse.from(
                    postService.getPostList(pageable, Long.parseLong(memberId))
            );
        } else {
            // 비로그인 사용자
            return PagingResponse.from(
                    postService.getPostList(pageable, null)
            );
        }
    }

    /**
     * 게시글 단건 조회
     */

    @GetMapping("/{post-id}")
    public PostResponse.Info getPost(
            @PathVariable("post-id") Long postId
    ){
        return postService.getPost(postId);
    }
    
    //TODO 게시물 수정

    /**
     * 게시글 삭제
     */
    @DeleteMapping("/{post-id}")
    public GlobalResponse deletePost(
            @Authenticate Long memberId,
            @PathVariable("post-id") Long postId
    ){
        postService.deletePost(memberId, postId);
        return GlobalResponse.builder().message("게시물 삭제가 완료되었습니다.").build();
    }

    /**
     * 로그인한 유저가 작성한 게시글 조회
     */

    @GetMapping("/my")
    public PagingResponse<PostResponse.Info> getMyPostList(
            @Authenticate Long memberId,
            @PageableDefault(page = 0, size = 10) Pageable pageable
    ){
        Page<PostResponse.Info> memberPostList = postService.getMemberPostList(memberId, pageable);
        return PagingResponse.from(memberPostList);
    }
}
