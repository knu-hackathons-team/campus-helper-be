package com.soft.campushelper.work.service;

import com.soft.campushelper.member.Member;
import com.soft.campushelper.member.service.MemberReaderService;
import com.soft.campushelper.post.Post;
import com.soft.campushelper.post.service.PostReaderService;
import com.soft.campushelper.work.Work;
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
    public void takeWork(Long memberId, Long postId){
        Member member = memberReaderService.getMemberById(memberId);
        Post post = postReaderService.getPostById(postId);
        //게시물에 수행자 배정
        post.assignWorker(member);

        //Work의 시작시간 설정 (현재시간으로설정)
        post.getWork().workStart();
    }

    @Transactional
    public void completeWork(Long memberId, Long postId) {
        Member member = memberReaderService.getMemberById(memberId);
        Post post = postReaderService.getPostById(postId);
        Work work = workReaderService.getWorkByPost(post);

        // 작업 수행자 검증
        if (!work.isCorrectWorker(member)) {
            throw new IllegalStateException("작업 수행자만 완료할 수 있습니다.");
        }

        // 작업 완료 처리
        work.complete();

        // 게시글 상태 업데이트
        post.complete();

        // 보상 지급
        member.increasePoint(post.getReward());
    }
}
