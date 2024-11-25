package com.soft.campushelper.member.repository;

import com.soft.campushelper.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {


    Optional<Member> findByLoginId(String loginId);
}
