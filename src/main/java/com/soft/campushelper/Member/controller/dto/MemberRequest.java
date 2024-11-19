package com.soft.campushelper.Member.controller.dto;

import lombok.Builder;

public class MemberRequest {

    @Builder
    public record Register(
            String loginId,
            String password,
            String nickname,
            String college
    ){

    }


    @Builder
    public record Login(
            String loginId,
            String password
    ){

    }
}
