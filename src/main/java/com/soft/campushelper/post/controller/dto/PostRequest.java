package com.soft.campushelper.post.controller.dto;

import com.soft.campushelper.Member.Member;
import com.soft.campushelper.post.FundingStatus;
import com.soft.campushelper.post.HelpCategory;
import com.soft.campushelper.post.Post;
import lombok.Builder;

import java.time.LocalDateTime;

public class PostRequest {

    //TODO raminingTime 받고 터지는시간 계산
    @Builder
    public record Add(
            String title,
            String content,
            int reward,
            boolean processStatus,
            boolean allowGroupFunding,
            String category,
            int remainingTime,
            double latitude,
            double longitude
    ){
        public Post toEntity(Member member) {
            return Post.builder()
                    .title(title)
                    .content(content)
                    .writer(member)
                    .reward(reward)
                    .allowGroupFunding(allowGroupFunding)
                    .processStatus(processStatus)
                    .category(HelpCategory.fromDescription(category))
                    .remainingTime(remainingTime)
                    .latitude(latitude)
                    .longitude(longitude)
                    .build();
        }

    }
}
