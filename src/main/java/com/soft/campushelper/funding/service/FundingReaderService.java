package com.soft.campushelper.funding.service;

import com.soft.campushelper.funding.Funding;
import com.soft.campushelper.funding.repository.FundingRepository;
import com.soft.campushelper.global.constants.MessageConstants;
import com.soft.campushelper.member.Member;
import com.soft.campushelper.post.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FundingReaderService {

    private final FundingRepository fundingRepository;

    public boolean existsByPostAndParticipant(Post post, Member member) {
        return fundingRepository.existsByPostAndParticipant(post, member);
    }

    @Transactional(readOnly = true)
    public Page<Funding> findAllByParticipant(Member participant, Pageable pageable) {
        return fundingRepository.findAllByParticipant(participant, pageable);
    }

    @Transactional(readOnly = true)
    public Page<Funding> findAllByParticipantAndPostAllowGroupFunding(Member participant, Pageable pageable) {
        return fundingRepository.findAllByParticipantAndPost_AllowGroupFunding(participant, true, pageable);
    }

    @Transactional(readOnly = true)
    public Funding getFundingByPostAndParticipant(Post post, Member member) {
        return fundingRepository.getFundingByPostAndParticipant(post, member).orElseThrow(() -> new IllegalStateException(MessageConstants.NOT_FUNDING_PARTICIPANT));
    }

}
