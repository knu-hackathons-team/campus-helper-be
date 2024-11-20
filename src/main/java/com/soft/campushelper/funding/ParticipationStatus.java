package com.soft.campushelper.funding;

@Getter
public enum ParticipationStatus {
    PENDING("대기중"),
    APPROVED("승인됨"),
    COMPLETED("완료됨");

    private final String description;

    ParticipationStatus(String description) {
        this.description = description;
    }
}