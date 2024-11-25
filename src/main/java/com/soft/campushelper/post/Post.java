package com.soft.campushelper.post;


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
            throw new IllegalStateException("펀딩이 불가능한 게시글입니다.");
        }
        this.currentParticipants++; //현재 펀딩참가자수 증가
        this.reward += fundingAmount; // 게시글의 보상금에서 펀딩 금액만큼 더해줌
    }


    // 수행자 할당 메서드
    public void assignWorker(Member worker) {
        if (this.processStatus != ProcessStatus.NOT_STARTED) {
            throw new IllegalStateException("이미 진행 중이거나 완료된 요청입니다.");
        }

        if (this.work != null) {
            throw new IllegalStateException("이미 수행자가 할당되었습니다.");
        }

        this.work = Work.builder()
                .post(this)
                .worker(worker)
                .build();

        this.processStatus = ProcessStatus.IN_PROGRESS;
    }
    public void complete() {
        if (this.processStatus != ProcessStatus.IN_PROGRESS) {
            throw new IllegalStateException("진행 중인 요청만 완료할 수 있습니다.");
        }
        this.processStatus = ProcessStatus.COMPLETED;

    }

}
