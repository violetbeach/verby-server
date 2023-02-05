package com.verby.core.common.error;

public enum ErrorCode {

    INVALID_INPUT_VALUE(400, "COMMON-001", "유효성 검증에 실패한 경우"),
    FILE_UPLOAD_CONFLICT(409, "COMMON-002", "파일 업로드 중 실패한 경우"),

    DUPLICATE_LOGIN_ID(400, "USER-001", "계정명이 중복된 경우"),
    UNAUTHORIZED(401, "USER-002", "인증에 실패한 경우"),
    USER_NOT_FOUND(404, "USER-003", "계정을 찾을 수 없는 경우"),
    ROLE_NOT_EXISTS(403, "USER-004", "권한이 부족한 경우"),
    TOKEN_NOT_EXISTS(404, "USER-005", "해당 key의 인증 토큰이 존재하지 않는 경우"),
    DUPLICATE_PHONE_NUMBER(400, "USER-006", "휴대폰 번호가 중복된 경우"),
    UNAVAILABLE_LOGIN_ID(400, "USER-007", "사용 불가한 로그인 ID일 경우"),

    CERTIFICATION_NOT_FOUND(404, "AUTH-001", "인증 정보가 존재하지 않는 경우"),
    INVALID_CERTIFICATION_NUMBER(403, "AUTH-002", "휴대전화 인증 번호가 유효하지 않은 경우"),
    EXPIRED_VERIFICATION_TOKEN(403, "AUTH-003", "인증 토큰이 만료된 경우"),
    INVALID_VERIFICATION_TOKEN(403, "AUTH-004", "토큰이 유효하지 않은 경우"),
    CERTIFICATION_TYPE_NOT_MATCH(403, "AUTH-005", "인증 타입이 일치하지 않은 경우"),


    ARTIST_NOT_FOUND(404, "ARTIST-001", "가수를 찾을 수 없는 경우"),

    SONG_NOT_FOUND(404, "SONG-001", "곡을 찾을 수 없는 경우"),

    CONTEST_NOT_FOUND(404, "CONTEST-001", "선정 곡을 찾을 수 없는 경우"),
    CONTEST_INVALID_DATE(400, "CONTEST-002", "선정 곡 날짜가 적절치 않은 경우"),

    COVER_NOT_FOUND(404, "COVER-001", "커버를 찾을 수 없는 경우"),
    LIKE_NOT_FOUND(404, "COVER-002", "좋아요를 찾을 수 없는 경우"),

    INTERNAL_SERVER_ERROR(500, "SERVER-001", "서버가 요청을 처리할 수 없는 경우"),

    RESOURCE_TYPE_MISMATCH(409, "STORAGE-001", "도메인이 리소스 타입을 허용하지 않는 경우")

    ;

    private final int status;
    private final String code;
    private final String description;

    ErrorCode(int status, String code, String description) {
        this.status = status;
        this.code = code;
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
