package com.soft.campushelper.post.controller.dto;

import com.soft.campushelper.post.HelpCategory;
import com.soft.campushelper.post.Post;
import com.soft.campushelper.post.ProcessStatus;
import com.soft.campushelper.work.WorkStatus;
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
            boolean removable,
            int currentParticipants,
            boolean isWorker,
            String finishContent

    ){
        public static Info from(Post post, boolean removable, boolean isWorker, String finishContent){
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
                    .removable(removable)
                    .currentParticipants(post.getCurrentParticipants())
                    .processingStatus(post.getProcessStatus())
                    .isWorker(isWorker)
                    .finishContent(finishContent)
                    .build();
        }

        public static Info from(Post post, boolean removable, boolean isWorker){
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
                    .removable(removable)
                    .currentParticipants(post.getCurrentParticipants())
                    .processingStatus(post.getProcessStatus())
                    .isWorker(isWorker)
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
                    .removable(false)
                    .currentParticipants(post.getCurrentParticipants())
                    .processingStatus(post.getProcessStatus())
                    .build();
        }
    }

    @Builder
    public record MyWorkInfo(
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
            boolean removable,
            int currentParticipants

    ){
        public static MyWorkInfo from(Post post){
            return MyWorkInfo.builder()
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
                    .removable(true)
                    .currentParticipants(post.getCurrentParticipants())
                    .build();
        }
    }
}
