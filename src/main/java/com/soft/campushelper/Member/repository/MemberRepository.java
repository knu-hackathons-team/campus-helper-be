package com.soft.campushelper.Member.repository;

import com.soft.campushelper.Member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
