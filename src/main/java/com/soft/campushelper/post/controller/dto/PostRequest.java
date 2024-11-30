package com.soft.campushelper.post.controller.dto;

import com.soft.campushelper.member.Member;
import com.soft.campushelper.post.HelpCategory;
import com.soft.campushelper.post.Post;
import lombok.Builder;

public class PostRequest {

    //TODO raminingTime 받고 터지는시간 계산
    @Builder
    public record Add(
            String title,
            String content,
            int reward,
            boolean allowGroupFunding,
            HelpCategory category,
            int remainingTime,
            double latitude,
            double longitude
    ) {
        public Post toEntity(Member member) {
            return Post.builder()
                    .title(title)
                    .content(content)
                    .writer(member)
                    .reward(reward)
                    .allowGroupFunding(allowGroupFunding)
                    .category(category)
                    .remainingTime(remainingTime)
                    .latitude(latitude)
                    .longitude(longitude)
                    .build();
        }

    }
}
