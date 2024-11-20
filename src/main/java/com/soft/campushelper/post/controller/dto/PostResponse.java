package com.soft.campushelper.post.controller.dto;

import com.soft.campushelper.post.FundingStatus;
import com.soft.campushelper.post.HelpCategory;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class PostResponse {
    @Builder
    public record Infos(
            List<Info> infos
    ){
        public static Infos from(List<PostResponse.Info> infoList){
            return Infos.builder().infos(infoList).build();
        }
    }
    @Builder
    public record Info(
            String subject,
            String userName,
            String postName,
            String description,
            HelpCategory category,
            FundingStatus fundingStatus,
            Integer distance,
            LocalDateTime endTime,
            Integer reward

    ){

    }
}
