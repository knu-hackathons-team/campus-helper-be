package com.soft.campushelper.work.service;

import com.soft.campushelper.global.constants.MessageConstants;
import com.soft.campushelper.global.exception.AuthenticationException;
import com.soft.campushelper.member.Member;
import com.soft.campushelper.member.service.MemberReaderService;
import com.soft.campushelper.post.Post;
import com.soft.campushelper.post.service.PostReaderService;
import com.soft.campushelper.work.Work;
import com.soft.campushelper.work.controller.dto.WorkRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WorkService {
    private final MemberReaderService memberReaderService;
    private final PostReaderService postReaderService;
    private final WorkReaderService workReaderService;

    @Transactional
    public void takeWork(Long memberId, Long postId) {
        Member member = memberReaderService.getMemberById(memberId);
        Post post = postReaderService.getPostById(postId);
        //게시물에 수행자 배정
        post.assignWorker(member);

        //Work의 시작시간 설정 (현재시간으로설정)
        post.getWork().workStart();
    }

    @Transactional
    public void completeWork(Long memberId, Long postId, WorkRequest.Finish request) {
        Member member = memberReaderService.getMemberById(memberId);
        Post post = postReaderService.getPostById(postId);
        Work work = workReaderService.getWorkByPost(post);

        // 작업 수행자 검증
        if (!work.isCorrectWorker(member)) {
            throw new IllegalStateException(MessageConstants.NOT_WORK_PERFORMER);
        }

        // 수행 완료 메시지 전송
        work.sendFinishContent(request.finishContent());
//        work.complete();

        // 게시글 상태 업데이트
//        post.complete();

        // 보상 지급
//        member.increasePoint(post.getReward());
    }

    @Transactional
    public void ratingToFinishWork(Long memberId, Long postId, WorkRequest.Rate request) {
        Member member = memberReaderService.getMemberById(memberId);
        Post post = postReaderService.getPostById(postId);
        Work work = workReaderService.getWorkByPost(post);

        //게시글의 소유자인지
        if (post.isWriter(member)) {
            //TODO 별점을 유저정보에 저장하는로직

            work.complete();

            post.complete();

            Member workerMember = memberReaderService.getMemberById(work.getWorker().getId());
            workerMember.increasePoint(post.getReward());

        } else {
            throw new AuthenticationException(MessageConstants.NOT_POST_WRITER);
        }

    }
}
