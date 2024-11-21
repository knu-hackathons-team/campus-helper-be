package com.soft.campushelper.post;

import lombok.Getter;

@Getter
public enum HelpCategory {
    HELP("도움"),
    INFO("정보");

    private final String description;

    HelpCategory(String description) {
        this.description = description;
    }

    public static HelpCategory fromDescription(String description) {
        return switch (description) {
            case "도움" -> HELP;
            case "정보" -> INFO;
            default -> throw new IllegalArgumentException("잘못된 카테고리입니다: " + description);
        };
    }
}
