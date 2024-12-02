package com.soft.campushelper.work;

import com.soft.campushelper.global.constants.MessageConstants;
import com.soft.campushelper.global.entity.BaseTimeEntity;
import com.soft.campushelper.member.Member;
import com.soft.campushelper.post.Post;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Work extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member worker;

    @Enumerated(EnumType.STRING)
    private WorkStatus status;

    @Builder.Default
    private String finishContent = "";

    private LocalDateTime startedAt;

    private LocalDateTime completedAt;

    // 상태 변경 메서드
    public void workStart() {
        this.status = WorkStatus.IN_PROGRESS;
        this.startedAt = LocalDateTime.now();
    }

    public void sendFinishContent(String content) {
        this.finishContent = content;
    }

    public boolean isExistFinishContent() {
        return this.finishContent != null;
    }

    public void complete() {
        if (this.status != WorkStatus.IN_PROGRESS) {
            throw new IllegalStateException(MessageConstants.INVALID_WORK_STATUS);
        }
        this.status = WorkStatus.COMPLETED;
        this.completedAt = LocalDateTime.now();
    }

    public boolean isCorrectWorker(Member member) {
        return this.worker.equals(member);
    }
}