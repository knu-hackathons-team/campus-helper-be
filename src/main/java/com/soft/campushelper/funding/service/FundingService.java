package com.soft.campushelper.funding.service;

import com.soft.campushelper.funding.Funding;
import com.soft.campushelper.funding.ParticipationStatus;
import com.soft.campushelper.global.constants.MessageConstants;
import com.soft.campushelper.member.Member;
import com.soft.campushelper.member.service.MemberReaderService;
import com.soft.campushelper.post.Post;
import com.soft.campushelper.post.ProcessStatus;
import com.soft.campushelper.post.service.PostReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FundingService {
    private final MemberReaderService memberReaderService;
    private final PostReaderService postReaderService;
    private final FundingReaderService fundingReaderService;
    private final FundingWriterService fundingWriterService;

    @Transactional
    public void participateInPost(Long memberId, Long postId) {
        Member member = memberReaderService.getMemberById(memberId);
        Post post = postReaderService.getPostById(postId);

        //펀딩 가능상태 체크
        if (!post.isAllowGroupFunding()) {
            throw new IllegalStateException(MessageConstants.FUNDING_NOT_ALLOWED);
        }
        // 이미 참여했는지 확인
        if (fundingReaderService.existsByPostAndParticipant(post, member)) {
            throw new IllegalStateException(MessageConstants.ALREADY_PARTICIPATED);
        }

        Funding funding = Funding.builder()
                .post(post)
                .participant(member)
                .amount(post.getReward()) //Post의 보상금만큼 펀딩
                .status(ParticipationStatus.IN_PROGRESS)
                .build();

        post.addParticipant(funding.getAmount());

        fundingWriterService.save(funding);

        //TODO 펀딩 후 유저 포인트 감소 로직
        member.decreasePoint(post.getReward());

    }

    @Transactional
    public void cancelFunding(Long memberId, Long postId) {
        Member member = memberReaderService.getMemberById(memberId);
        Post post = postReaderService.getPostById(postId);

        // 게시글 작성자는 펀딩 취소 불가
        if (post.isWriter(member)) {
            throw new IllegalStateException(MessageConstants.WRITER_CANNOT_CANCEL_FUNDING);
        }

        // 펀딩 참여 여부 확인
        Funding funding = fundingReaderService.getFundingByPostAndParticipant(post, member);


        // 진행 중인 상태인지 확인
        if (post.getProcessStatus() != ProcessStatus.NOT_STARTED) {
            throw new IllegalStateException(MessageConstants.CANNOT_CANCEL_IN_PROGRESS);
        }

        // 펀딩 금액만큼 포인트 환불
        member.increasePoint(funding.getAmount());

        // 게시글의 참여자 수와 보상금 감소
        post.removeParticipant(funding.getAmount());

        // 펀딩 기록 삭제
        fundingWriterService.delete(funding);
    }
}
