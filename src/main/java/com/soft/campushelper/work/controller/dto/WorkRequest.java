package com.soft.campushelper.work.controller.dto;

import lombok.Builder;

public class WorkRequest {
    @Builder
    public record Finish(
            String finishContent
    ) {

    }

    @Builder
    public record Rate(
            int rate
    ) {


    }

}
