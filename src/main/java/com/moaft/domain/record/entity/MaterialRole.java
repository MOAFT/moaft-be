package com.moaft.domain.record.entity;

public enum MaterialRole {
    AI_INPUT,               // AI에 넣은 입력 자료
    REFERENCE,              // 참고 자료
    INTERMEDIATE_OUTPUT,    // 제작 단계의 중간 결과물
    FINAL_OUTPUT,           // 최종 결과물
    VARIATION_OUTPUT        // 변화 항목의 결과물
}