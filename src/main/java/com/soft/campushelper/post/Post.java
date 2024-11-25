package com.soft.campushelper.post;


import com.soft.campushelper.global.constants.MessageConstants;
import com.soft.campushelper.member.Member;
import com.soft.campushelper.global.entity.BaseTimeEntity;
import com.soft.campushelper.work.Work;
import jakarta.persistence.*;
import lombok.*;

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
    @Enumerated(EnumType.STRING)
    private ProcessStatus processStatus = ProcessStatus.NOT_STARTED;

    @Column(nullable = false)
    @Builder.Default
    private int reward = 0;

    @Column(nullable = false)
    @Builder.Default
    private int currentParticipants = 1;

    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL)
    private Work work;

    //TODO 펀딩 연관관계

    public boolean isWriter(Member member){
        return this.writer.equals(member);
    }

    public void addParticipant(int fundingAmount) {
        if (!isAllowGroupFunding()) {
            throw new IllegalStateException(MessageConstants.FUNDING_NOT_ALLOWED);
        }
        this.currentParticipants++; //현재 펀딩참가자수 증가
        this.reward += fundingAmount; // 게시글의 보상금에서 펀딩 금액만큼 더해줌
    }


    // 수행자 할당 메서드
    public void assignWorker(Member worker) {
        if (this.processStatus != ProcessStatus.NOT_STARTED) {
            throw new IllegalStateException(MessageConstants.INVALID_WORK_STATUS);
        }

        if (this.work != null) {
            throw new IllegalStateException(MessageConstants.ALREADY_HAS_WORKER);
        }

        this.work = Work.builder()
                .post(this)
                .worker(worker)
                .build();

        this.processStatus = ProcessStatus.IN_PROGRESS;
    }
    public void complete() {
        if (this.processStatus != ProcessStatus.IN_PROGRESS) {
            throw new IllegalStateException(MessageConstants.INVALID_WORK_STATUS);
        }
        this.processStatus = ProcessStatus.COMPLETED;

    }

}
