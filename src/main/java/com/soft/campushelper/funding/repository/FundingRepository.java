package com.soft.campushelper.funding.repository;

import com.soft.campushelper.member.Member;
import com.soft.campushelper.funding.Funding;
import com.soft.campushelper.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundingRepository extends JpaRepository<Funding, Long> {

    boolean existsByPostAndParticipant(Post post, Member member);
}
