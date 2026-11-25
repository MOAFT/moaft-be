package com.moaft.domain.user.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Interest {
    GRAPHIC_DESIGN("그래픽 디자인"),
    UI_UX_DESIGN("UI/UX 디자인"),
    BRANDING("브랜딩"),
    ILLUSTRATION("일러스트"),
    ART_3D("3D 아트"),
    CHARACTER_DESIGN("캐릭터 디자인"),
    PHOTOGRAPHY("사진"),
    FASHION("패션"),
    VIDEO_PRODUCTION("영상 제작"),
    VIDEO_EDITING("영상 편집"),
    ANIMATION("애니메이션"),
    SHORT_FORM("숏폼 콘텐츠"),
    SNS_CONTENT("SNS 콘텐츠"),
    MUSIC_AUDIO("음악·오디오"),
    WRITING("글쓰기"),
    STORYTELLING("스토리텔링"),
    MARKETING("마케팅"),
    ADVERTISING("광고"),
    CONTENT_PLANNING("콘텐츠 기획"),
    EDUCATION("교육"),
    PROGRAMMING("프로그래밍"),
    WEB_APP_DEVELOPMENT("웹·앱 개발"),
    DATA_ANALYSIS("데이터 분석"),
    WORK_AUTOMATION("업무 자동화"),
    PRODUCTIVITY("생산성"),
    GAME("게임"),
    FILM_DRAMA("영화·드라마"),
    TRAVEL("여행"),
    FOOD_COOKING("푸드·요리"),
    LIFESTYLE("라이프스타일");

    private final String label;
}
