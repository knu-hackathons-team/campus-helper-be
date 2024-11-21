package com.soft.campushelper.post;


import com.soft.campushelper.Member.Member;
import com.soft.campushelper.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member writer;

    private String title;

    private String content;

    private HelpCategory category;

    private FundingStatus fundingStatus;

    private double latitude;

    private double longitude;

    private int distance;

    private LocalDateTime endTime;

    @Builder.Default
    private int reward = 0;  // 보상금 (펀딩금액이 더해질 수 있음)


    @Builder.Default
    private int currentParticipants = 0;  // 현재 참여자 수

    //TODO 펀딩

    public boolean isWriter(Member member){
        return this.writer.equals(member);
    }
    public boolean isFundingEnabled() {
        return this.fundingStatus == FundingStatus.ENABLED;
    }

    public void addParticipant(int fundingAmount) {
        if (!isFundingEnabled()) {
            throw new IllegalStateException("펀딩이 불가능한 게시글입니다.");
        }
        this.currentParticipants++; //현재 펀딩참가자수 증가
        this.reward += fundingAmount; // 게시글의 보상금에서 펀딩 금액만큼 더해줌
    }

}
