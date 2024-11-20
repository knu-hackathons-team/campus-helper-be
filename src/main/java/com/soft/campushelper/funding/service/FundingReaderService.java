package com.soft.campushelper.funding.service;

import com.soft.campushelper.Member.Member;
import com.soft.campushelper.funding.repository.FundingRepository;
import com.soft.campushelper.post.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FundingReaderService {

    private final FundingRepository fundingRepository;

    public boolean existsByPostAndParticipant(Post post, Member member){
        return fundingRepository.existsByPostAndParticipant(post, member);
    }
}
