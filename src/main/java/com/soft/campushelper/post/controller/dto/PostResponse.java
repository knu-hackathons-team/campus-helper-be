package com.soft.campushelper.post.controller.dto;

import com.soft.campushelper.Member.Member;
import com.soft.campushelper.post.FundingStatus;
import com.soft.campushelper.post.HelpCategory;
import com.soft.campushelper.post.Post;
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
            Long id,
            String college,
            String writer,
            String title,
            String content,
            HelpCategory category,
            FundingStatus fundingStatus,
            double latitude,
            double longitude,
            LocalDateTime endTime,
            Integer reward

    ){
        public static Info from(Post post){
            return Info.builder()
                    .id(post.getId())
                    .college(post.getWriter().getCollege())
                    .writer(post.getWriter().getNickname())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .category(post.getCategory())
                    .fundingStatus(post.getFundingStatus())
                    .latitude(post.getLatitude())
                    .longitude(post.getLongitude())
                    .endTime(post.getEndTime())
                    .reward(post.getReward())
                    .build();
        }
    }
}
