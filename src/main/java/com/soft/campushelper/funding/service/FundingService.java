package com.soft.campushelper.funding.service;

import com.soft.campushelper.Member.Member;
import com.soft.campushelper.Member.service.MemberReaderService;
import com.soft.campushelper.funding.Funding;
import com.soft.campushelper.funding.ParticipationStatus;
import com.soft.campushelper.post.Post;
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
        if (!post.isFundingEnabled()) {
            throw new IllegalStateException("펀딩이 불가능한 게시글입니다.");
        }
        // 이미 참여했는지 확인
        if (fundingReaderService.existsByPostAndParticipant(post, member)) {
            throw new IllegalStateException("이미 참여한 요청입니다.");
        }

        post.addParticipant();

        Funding funding = Funding.builder()
                .post(post)
                .participant(member)
                .amount(100) //TODO 펀딩 금액 조절
                .build();

        fundingWriterService.save(funding);

        //TODO 펀딩 후 유저 포인트 감소 로직

        member.decreasePoint(100);

    }
}
