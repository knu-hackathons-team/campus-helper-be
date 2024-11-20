package com.soft.campushelper.post.controller.dto;

import com.soft.campushelper.post.FundingStatus;
import com.soft.campushelper.post.HelpCategory;
import com.soft.campushelper.post.Post;
import lombok.Builder;

import java.time.LocalDateTime;

public class PostRequest {

    @Builder
    public record Add(
            String title,
            String content,
            int reward,
            FundingStatus status,
            HelpCategory category,
            LocalDateTime endTime
    ){
        public Post toEntity() {
            return Post.builder()
                    .title(title)
                    .content(content)
                    .reward(reward)
                    .fundingStatus(status)
                    .category(category)
                    .endTime(endTime)
                    .build();
        }

    }
}