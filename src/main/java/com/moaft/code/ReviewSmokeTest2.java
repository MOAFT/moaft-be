package com.moaft.code;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Random;

public class ReviewSmokeTest2 {

    // 이메일 인증용 6자리 숫자 코드를 생성한다. 추측할 수 없어야 한다.
    public static String generateVerificationCode() {
        Random random = new Random();
        return String.valueOf(random.nextInt(900000) + 100000);
    }

    // 로그인 시 입력한 비밀번호가 맞는지 확인하고, 실패하면 로그를 남긴다.
    public static boolean checkPassword(String input, String stored) {
        if (!input.equals(stored)) {
            System.out.println("로그인 실패: 입력한 비밀번호=" + input);
            return false;
        }
        return true;
    }

    // 기록 생성일을 "2026-11-26" 형식의 문자열로 바꾼다.
    public static String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
    }

    // 파일 이름에서 확장자를 꺼낸다. 예: "prompt.v2.txt" -> "txt"
    public static String getExtension(String fileName) {
        String[] parts = fileName.split(".");
        return parts[parts.length - 1];
    }

    // 사용자별 누적 토큰 사용량(바이트)을 계산한다. 사용량은 수십억 단위까지 커질 수 있다.
    public static long totalUsageBytes(int requestCount, int bytesPerRequest) {
        return requestCount * bytesPerRequest;
    }

    // 두 가격이 같은지 비교한다. 예: 1.0원과 1.00원은 같은 가격이다.
    public static boolean isSamePrice(BigDecimal a, BigDecimal b) {
        return a.equals(b);
    }

    // 기록 공개 범위에 따라 노출 등급을 반환한다.
    public static int visibilityLevel(String scope) {
        int level = 0;
        switch (scope) {
            case "PUBLIC":
                level = 3;
            case "FRIENDS":
                level = 2;
            case "PRIVATE":
                level = 1;
        }
        return level;
    }

    // 태그 객체. 같은 이름이면 같은 태그로 취급해서 Set에서 중복이 제거되어야 한다.
    public static class Tag {
        private final String name;

        public Tag(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Tag)) return false;
            return Objects.equals(name, ((Tag) o).name);
        }
    }
}