package com.soft.campushelper.member.controller;

import com.soft.campushelper.member.controller.dto.MemberRequest;
import com.soft.campushelper.member.controller.dto.MemberResponse;
import com.soft.campushelper.member.service.MemberService;
import com.soft.campushelper.global.annotation.Authenticate;
import com.soft.campushelper.global.dto.GlobalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register")
    public GlobalResponse register(
            @RequestBody MemberRequest.Register request
            ){
        memberService.register(request);
        return GlobalResponse.builder().message("회원가입이 완료되었습니다.").build();
    }
    @PostMapping("/login")
    public ResponseEntity<MemberResponse.Login> login(
            @RequestBody MemberRequest.Login request
    ) {
        String token = memberService.login(request);

        return ResponseEntity.ok()
                .header("Authorization", token)
                .body(MemberResponse.Login.from(token));
    }

    @GetMapping("/info")
    public ResponseEntity<MemberResponse.Info> getMemberInfo(
            @Authenticate Long memberId
    ) {
        return ResponseEntity.ok(memberService.getMemberInfo(memberId));
    }

    @GetMapping("/point")
    public GlobalResponse addPoint(
            @Authenticate Long memberId
    ){
        memberService.pointUp(memberId);
        return GlobalResponse.builder().message("포인트 증가").build();
    }
}
