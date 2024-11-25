package com.soft.campushelper.post.service;

import com.soft.campushelper.global.constants.MessageConstants;
import com.soft.campushelper.member.Member;
import com.soft.campushelper.member.service.MemberReaderService;
import com.soft.campushelper.global.exception.AuthenticationException;
import com.soft.campushelper.post.Post;
import com.soft.campushelper.post.controller.dto.PostRequest;
import com.soft.campushelper.post.controller.dto.PostResponse;
import com.soft.campushelper.work.service.WorkReaderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final MemberReaderService memberReaderService;
    private final PostReaderService postReaderService;
    private final PostWriterService postWriterService;
    private final WorkReaderService workReaderService;

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
    public Page<PostResponse.Info> getPostList(Pageable pageable, Long memberId){
        return postReaderService.getPostList(pageable).
                map(post -> {
            boolean isRemovable = false;
            boolean isWorker = false;
            if (memberId == null) {
                return PostResponse.Info.from(post, isRemovable, isWorker);
            }

            Member member = memberReaderService.getMemberById(memberId);
            isRemovable = post.isWriter(member);
            isWorker = post.getWork() != null && post.getWork().isCorrectWorker(member);

            return PostResponse.Info.from(post, isRemovable, isWorker);
        });
    }

    /**
     * 게시글 단건 조회
     */
    @Transactional(readOnly = true)
    public PostResponse.Info getPost(Long postId, Long memberId) {
        Post post = postReaderService.getPostById(postId);
        boolean isRemovable = false;
        boolean isWorker = false;
        if (memberId == null) {
            return PostResponse.Info.from(post, isRemovable, isWorker);
        }

        Member member = memberReaderService.getMemberById(memberId);
        isRemovable = post.isWriter(member);
        isWorker = post.getWork() != null && post.getWork().isCorrectWorker(member);

        return PostResponse.Info.from(post, isRemovable, isWorker);
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
            throw new AuthenticationException(MessageConstants.NOT_POST_WRITER);
        }

        postWriterService.delete(post);
    }

    /**
     * 로그인한 유저가 작성한 게시물 리스트 반환
     */

    @Transactional(readOnly = true)
    public Page<PostResponse.Info> getMemberPostList(Long memberId, Pageable pageable){
        Member member = memberReaderService.getMemberById(memberId);
        Page<Post> postList = postReaderService.getPostListByWriter(member, pageable);
        return postList.map(post -> {
                    boolean isWorker = post.getWork() != null && post.getWork().isCorrectWorker(member);
                    return PostResponse.Info.from(post, true, isWorker);
                }
        );
    }

    @Transactional(readOnly = true)
    public Page<PostResponse.MyWorkInfo> getPostListByWorker(Long memberId, Pageable pageable){
        Member member = memberReaderService.getMemberById(memberId);
        return workReaderService.findAllByWorker(member, pageable)
                .map(
                        work -> {
                            Post post = work.getPost();
                            return PostResponse.MyWorkInfo.from(post, work.getStatus());
                        }
                );

    }

}
