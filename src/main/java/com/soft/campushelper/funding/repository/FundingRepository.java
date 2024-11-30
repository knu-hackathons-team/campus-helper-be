package com.soft.campushelper.funding.repository;

import com.soft.campushelper.member.Member;
import com.soft.campushelper.funding.Funding;
import com.soft.campushelper.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FundingRepository extends JpaRepository<Funding, Long> {

    boolean existsByPostAndParticipant(Post post, Member member);

    Page<Funding> findAllByParticipant(Member participant, Pageable pageable);

    Page<Funding> findAllByParticipantAndPost_AllowGroupFunding(
            Member participant,
            boolean allowGroupFunding,
            Pageable pageable
    );

    Optional<Funding> getFundingByPostAndParticipant(Post post, Member member);
}
