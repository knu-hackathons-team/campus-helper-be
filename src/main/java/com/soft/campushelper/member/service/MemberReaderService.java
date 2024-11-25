package com.soft.campushelper.member.service;

import com.soft.campushelper.global.constants.MessageConstants;
import com.soft.campushelper.member.Member;
import com.soft.campushelper.member.repository.MemberRepository;
import com.soft.campushelper.global.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberReaderService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public Member getMemberById(Long memberId){
        return memberRepository.findById(memberId).orElseThrow(
                () -> new EntityNotFoundException(MessageConstants.MEMBER_NOT_FOUND)
        );
    }

    @Transactional(readOnly = true)
    public Optional<Member> getMemberByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId);
    }

    @Transactional(readOnly = true)
    public Optional<Member> getMemberByNickname(String nickname) {
        return memberRepository.findByNickname(nickname);
    }
}
