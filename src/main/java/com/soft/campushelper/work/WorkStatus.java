package com.soft.campushelper.work;

import lombok.Getter;

@Getter
public enum WorkStatus {
    NOT_STARTED("미시작"),
    IN_PROGRESS("진행중"),
    COMPLETED("완료");

    private final String description;

    WorkStatus(String description) {
        this.description = description;
    }
}
