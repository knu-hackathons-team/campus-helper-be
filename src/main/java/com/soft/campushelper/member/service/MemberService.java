package com.soft.campushelper.member.service;

import com.soft.campushelper.global.constants.MessageConstants;
import com.soft.campushelper.member.Member;
import com.soft.campushelper.member.controller.dto.MemberRequest;
import com.soft.campushelper.member.Role;
import com.soft.campushelper.member.controller.dto.MemberResponse;
import com.soft.campushelper.global.auth.JwtProvider;
import com.soft.campushelper.global.exception.AuthenticationException;
import com.soft.campushelper.global.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberReaderService memberReaderService;
    private final MemberWriterService memberWriterService;
    private final JwtProvider jwtProvider;

    @Transactional
    public void register(MemberRequest.Register request) {
        memberReaderService.getMemberByLoginId(request.loginId())
                .ifPresent(member -> {
                    throw new IllegalArgumentException(MessageConstants.DUPLICATE_LOGIN_ID);
                });
        memberReaderService.getMemberByNickname(request.nickname())
                .ifPresent(member -> {
                    throw new IllegalArgumentException(MessageConstants.DUPLICATE_NICKNAME);
                });

        Member member = Member.builder()
                .loginId(request.loginId())
                .password(hashPassword(request.password())) // 간단한 해시 처리
                .nickname(request.nickname())
                .college(request.college())
                .role(Role.USER)
                .build();

        memberWriterService.save(member);
    }
    @Transactional
    public String login(MemberRequest.Login request) {
        Member member = memberReaderService.getMemberByLoginId(request.loginId()).orElseThrow(
                () -> new EntityNotFoundException("존재하지 않는 Id 입니다.")
        );

        if (!member.getPassword().equals(hashPassword(request.password()))) {
            throw new AuthenticationException(MessageConstants.INVALID_PASSWORD);
        }

        return jwtProvider.createToken(member.getId(), member.getRole());
    }

    @Transactional
    public MemberResponse.Info getMemberInfo(Long memberId){
        Member member = memberReaderService.getMemberById(memberId);

        return MemberResponse.Info.from(member);


    }

    @Transactional
    public void pointUp(Long memberId) {
        Member member = memberReaderService.getMemberById(memberId);
        member.increasePoint(100000);
    }

    @Transactional
    public void withdrawPoint(Long memberId, MemberRequest.Withdraw request) {
        Member member = memberReaderService.getMemberById(memberId);
        member.decreasePoint(request.point());
    }


    private String hashPassword(String password) {
        // MessageDigest를 사용한 간단한 해시 처리
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("비밀번호 해시 처리 실패", e);
        }
    }


}
