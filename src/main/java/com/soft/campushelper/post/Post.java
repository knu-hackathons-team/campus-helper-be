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
    @JoinColumn(name = "member_id", nullable = false)
    private Member writer;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HelpCategory category;

    @Column(nullable = false)
    @Builder.Default
    private boolean allowGroupFunding = false;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    @Column(nullable = false)
    @Builder.Default
    private int distance = 0;

    @Column(nullable = false)
    @Builder.Default
    private int remainingTime = 0;

    @Column(nullable = false)
    @Builder.Default
    private boolean processStatus = false;

    @Column(nullable = false)
    @Builder.Default
    private int reward = 0;

    @Column(nullable = false)
    @Builder.Default
    private int currentParticipants = 1;

    //TODO 펀딩

    public boolean isWriter(Member member){
        return this.writer.equals(member);
    }

    public void addParticipant(int fundingAmount) {
        if (!isAllowGroupFunding()) {
            throw new IllegalStateException("펀딩이 불가능한 게시글입니다.");
        }
        this.currentParticipants++; //현재 펀딩참가자수 증가
        this.reward += fundingAmount; // 게시글의 보상금에서 펀딩 금액만큼 더해줌
    }

}
