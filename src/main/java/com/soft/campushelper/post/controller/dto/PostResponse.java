package com.soft.campushelper.post.controller.dto;

import com.soft.campushelper.post.HelpCategory;
import com.soft.campushelper.post.Post;
import com.soft.campushelper.post.ProcessStatus;
import lombok.Builder;

import java.time.format.DateTimeFormatter;
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
            ProcessStatus processingStatus,
            boolean allowGroupFunding,
            double latitude,
            double longitude,
            Integer reward,
            String createdAt,
            boolean removeable

    ){
        public static Info from(Post post, boolean removeable){
            return Info.builder()
                    .id(post.getId())
                    .college(post.getWriter().getCollege())
                    .writer(post.getWriter().getNickname())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .category(post.getCategory())
                    .allowGroupFunding(post.isAllowGroupFunding())
                    .processingStatus(post.getProcessStatus())
                    .latitude(post.getLatitude())
                    .longitude(post.getLongitude())
                    .reward(post.getReward())
                    .createdAt(post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .removeable(removeable)
                    .build();
        }
        public static Info from(Post post){
            return Info.builder()
                    .id(post.getId())
                    .college(post.getWriter().getCollege())
                    .writer(post.getWriter().getNickname())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .category(post.getCategory())
                    .allowGroupFunding(post.isAllowGroupFunding())
                    .processingStatus(post.getProcessStatus())
                    .latitude(post.getLatitude())
                    .longitude(post.getLongitude())
                    .reward(post.getReward())
                    .createdAt(post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .removeable(false)
                    .build();
        }
    }
}
