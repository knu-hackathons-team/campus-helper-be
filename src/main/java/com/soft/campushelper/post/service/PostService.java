package com.soft.campushelper.post.service;

import com.soft.campushelper.member.Member;
import com.soft.campushelper.member.service.MemberReaderService;
import com.soft.campushelper.global.exception.AuthenticationException;
import com.soft.campushelper.post.Post;
import com.soft.campushelper.post.controller.dto.PostRequest;
import com.soft.campushelper.post.controller.dto.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final MemberReaderService memberReaderService;
    private final PostReaderService postReaderService;
    private final PostWriterService postWriterService;

    /**
     * 게시물을 추가하는 메서드
     */
    @Transactional
    public void addPost(Long memberId, PostRequest.Add request){
        Member member = memberReaderService.getMemberById(memberId);

        Post post = request.toEntity(member);

        postWriterService.save(post);

        //TODO 유저 포인트 감소 로직
        member.decreasePoint(request.reward());
    }

    /**
     * 게시물 목록을 페이징으로 반환하는 메서드
     * 비회원, 회원 모두 가능
     */
    @Transactional(readOnly = true)
    public Page<PostResponse.Info> getPostList(Pageable pageable){
        Page<Post> postList = postReaderService.getPostList(pageable);
        return postList.map(PostResponse.Info::from);
    }

    /**
     * 게시글 단건 조회
     */
    @Transactional(readOnly = true)
    public PostResponse.Info getPost(Long memberId, Long postId){
        Member member = memberReaderService.getMemberById(memberId);
        Post post = postReaderService.getPostById(postId);
        return PostResponse.Info.from(post);
    }

    /**
     * 게시물을 삭제하는 메서드
     * @param memberId
     * @param postId
     */
    @Transactional
    public void deletePost(Long memberId, Long postId){
        Member member = memberReaderService.getMemberById(memberId);
        Post post = postReaderService.getPostById(postId);

        if (!post.isWriter(member)) {
            throw new AuthenticationException("게시글 삭제 권한이 없습니다.");
        }

        postWriterService.delete(post);
    }

    @Transactional(readOnly = true)
    public Page<PostResponse.Info> getMemberPostList(Long memberId, Pageable pageable){
        Member member = memberReaderService.getMemberById(memberId);
        Page<Post> postList = postReaderService.getPostListByWriter(member, pageable);

        return postList.map(PostResponse.Info::from);
    }

}
