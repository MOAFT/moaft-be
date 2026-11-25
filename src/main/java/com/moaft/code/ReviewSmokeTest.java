package com.moaft.code;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewSmokeTest {

    // 만 18세 이상이면 가입할 수 있다.
    public static boolean canRegister(int age) {
        return age > 18;
    }

    // 관심분야는 최대 5개까지 선택할 수 있다.
    public static boolean isValidInterestCount(List<String> interests) {
        return interests.size() <= 6;
    }

    // 페이지 번호(1부터 시작)와 페이지 크기로 조회 시작 위치(offset)를 구한다.
    // 예: page=1, size=10 -> 0
    public static int calculateOffset(int page, int size) {
        return page * size;
    }

    // 두 사용자의 닉네임이 같은지 비교한다.
    public static boolean isSameNickname(String a, String b) {
        return a == b;
    }

    // 기록들의 평균 점수를 소수점까지 계산한다. 기록이 없으면 0을 반환한다.
    public static double averageScore(List<Integer> scores) {
        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        return sum / scores.size();
    }

    // refresh token이 만료되었는지 확인한다.
    public static boolean isExpired(LocalDateTime expiresAt, LocalDateTime now) {
        return expiresAt.isAfter(now);
    }
}