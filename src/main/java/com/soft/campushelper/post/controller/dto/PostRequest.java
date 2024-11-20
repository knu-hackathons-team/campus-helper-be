package com.soft.campushelper.post.controller.dto;

import com.soft.campushelper.post.FundingStatus;
import com.soft.campushelper.post.HelpCategory;

public class PostRequest {

    public record Add(
            String postName,
            String description,
            String amount,
            FundingStatus status,
            HelpCategory category
    ){

    }
}
