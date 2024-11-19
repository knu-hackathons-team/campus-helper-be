package com.soft.campushelper.Member.controller.dto;

import lombok.Builder;

public class MemberResponse {

    @Builder
    public record Login(
            String jwt
    ){
        public static MemberResponse.Login from(String jwt){
            return Login.builder().jwt(jwt).build();
        }
    }
}
