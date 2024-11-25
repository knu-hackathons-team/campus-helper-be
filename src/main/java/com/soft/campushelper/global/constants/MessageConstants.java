package com.soft.campushelper.global.constants;

public final class MessageConstants {

    // 회원 관련
    public static final String MEMBER_NOT_FOUND = "존재하지 않는 회원입니다.";
    public static final String DUPLICATE_LOGIN_ID = "이미 존재하는 ID입니다.";
    public static final String INVALID_PASSWORD = "비밀번호가 일치하지 않습니다.";
    public static final String INSUFFICIENT_POINTS = "포인트가 부족합니다.";

    // 게시글 관련
    public static final String POST_NOT_FOUND = "존재하지 않는 게시글입니다.";
    public static final String NOT_POST_WRITER = "게시글 작성자가 아닙니다.";
    public static final String EXPIRED_POST = "만료된 게시글입니다.";
    public static final String INVALID_POST_STATUS = "잘못된 게시글 상태입니다.";

    // 펀딩 관련
    public static final String FUNDING_NOT_ALLOWED = "펀딩이 불가능한 게시글입니다.";
    public static final String ALREADY_PARTICIPATED = "이미 참여한 펀딩입니다.";
    public static final String INVALID_FUNDING_AMOUNT = "잘못된 펀딩 금액입니다.";

    // 수행 관련
    public static final String WORK_NOT_FOUND = "존재하지 않는 작업입니다.";
    public static final String ALREADY_HAS_WORKER = "이미 수행자가 있습니다.";
    public static final String NOT_WORK_PERFORMER = "작업 수행자가 아닙니다.";
    public static final String INVALID_WORK_STATUS = "잘못된 작업 상태입니다.";
    public static final String WORK_ALREADY_COMPLETED = "이미 완료된 작업입니다.";

    // 인증 관련
    public static final String INVALID_TOKEN = "유효하지 않은 토큰입니다.";
    public static final String EXPIRED_TOKEN = "만료된 토큰입니다.";
    public static final String LOGIN_REQUIRED = "로그인이 필요합니다.";

    // 공통
    public static final String INTERNAL_SERVER_ERROR = "서버 내부 오류가 발생했습니다.";
    public static final String INVALID_REQUEST = "잘못된 요청입니다.";

    private MessageConstants(){

    }
}
