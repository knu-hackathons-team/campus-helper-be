package com.soft.campushelper.Member.controller;

import com.soft.campushelper.Member.controller.dto.MemberRequest;
import com.soft.campushelper.Member.controller.dto.MemberResponse;
import com.soft.campushelper.Member.service.MemberService;
import com.soft.campushelper.global.dto.GlobalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public MemberResponse.Login login(
            @RequestBody MemberRequest.Login request
    ){
        String token = memberService.login(request);

        return MemberResponse.Login.from(token);
    }
}
