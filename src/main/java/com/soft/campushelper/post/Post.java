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

    private int distance;

    private LocalDateTime endTime;

    private int reward;

    private int currentParticipants;

    //TODO 펀딩

    public boolean isWriter(Member member){
        return this.writer.equals(member);
    }
    public boolean isFundingEnabled() {
        return this.fundingStatus == FundingStatus.ENABLED;
    }

    public void addParticipant() {
        if (!isFundingEnabled()) {
            throw new IllegalStateException("펀딩이 불가능한 게시글입니다.");
        }
        this.currentParticipants++;
    }


}
