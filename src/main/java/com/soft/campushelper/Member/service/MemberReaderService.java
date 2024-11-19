package com.soft.campushelper.Member.service;

import com.soft.campushelper.Member.Member;
import com.soft.campushelper.Member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberReaderService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public Optional<Member> getMemberByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId);
    }
}
