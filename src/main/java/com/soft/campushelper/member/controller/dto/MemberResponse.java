package com.soft.campushelper.member.controller.dto;

import com.soft.campushelper.member.Member;
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

    @Builder
    public record Info(
            String nickname,
            String college,
            int point
    ){
        public static MemberResponse.Info from(Member member){
            return Info.builder()
                    .nickname(member.getNickname())
                    .college(member.getCollege())
                    .point(member.getPoint())
                    .build();
        }

    }
}
