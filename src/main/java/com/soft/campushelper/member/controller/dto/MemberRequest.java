package com.soft.campushelper.member.controller.dto;

import lombok.Builder;

public class MemberRequest {

    @Builder
    public record Register(
            String loginId,
            String password,
            String nickname,
            String college
    ) {

    }


    @Builder
    public record Login(
            String loginId,
            String password
    ) {

    }

    @Builder
    public record Withdraw(
            int point
    ) {

    }
}
