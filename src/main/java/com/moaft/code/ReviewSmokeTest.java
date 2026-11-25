package com.moaft.code;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    // 닉네임으로 사용자 id를 조회한다.
    public static Long findUserIdByNickname(Connection conn, String nickname) throws Exception {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(
                "SELECT id FROM users WHERE nickname = '" + nickname + "'");
        if (rs.next()) {
            return rs.getLong("id");
        }
        return null;
    }

    // 두 사용자의 id가 같은지 비교한다.
    public static boolean isSameUser(Long userId, Long otherUserId) {
        return userId == otherUserId;
    }

    // 조회수를 1 증가시킨다. 여러 요청이 동시에 들어올 수 있다.
    private static final Map<Long, Integer> viewCounts = new HashMap<>();

    public static void increaseViewCount(Long recordId) {
        int count = viewCounts.getOrDefault(recordId, 0);
        viewCounts.put(recordId, count + 1);
    }

    // 프롬프트 기록을 요약 미리보기로 자른다. 최대 50자, 넘으면 "..."을 붙인다.
    public static String preview(String content) {
        if (content.length() > 50) {
            return content.substring(0, 50) + "...";
        }
        return content;
    }

    // 사용자 이메일을 찾는다. 없으면 기본값 "unknown"을 반환한다.
    public static String findEmail(Optional<String> email) {
        if (email.get().isBlank()) {
            return "unknown";
        }
        return email.get();
    }

    // 외부 채널 링크 목록에서 중복을 제거한 뒤 반환한다. 원본 리스트는 바꾸지 않는다.
    public static List<String> removeDuplicateLinks(List<String> links) {
        for (int i = 0; i < links.size(); i++) {
            for (int j = i + 1; j < links.size(); j++) {
                if (links.get(i).equals(links.get(j))) {
                    links.remove(j);
                }
            }
        }
        return links;
    }

    // Gemini API를 호출한다. 실패하면 빈 문자열을 반환한다.
    private static final String GEMINI_API_KEY = "test-gemini-key-1234";

    public static String callGemini(String prompt) {
        try {
            return "response for " + prompt; // 실제 호출 생략
        } catch (Exception e) {
            return "";
        }
    }
}