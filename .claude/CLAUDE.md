# MOAFT Backend (moaft-be)

프롬프트 기록 웹사이트 MOAFT의 백엔드.

## 기술 스택
- Java 21, Spring Boot 4, Gradle
- MySQL + Spring Data JPA
- Spring AI (Google GenAI / Gemini)
- Lombok

## 패키지 구조
- `com.moaft.domain.{도메인}`: 도메인별 코드 (ai, record, user)
- `com.moaft.global`: config, exception, response, security

## 컨벤션
- 용어는 `member` 대신 `user`를 쓴다. (`user_id`, `USER_NOT_FOUND`)
- API URL은 kebab-case, 리소스는 단수형으로 쓰고 `me`를 쓰지 않는다. (예: `/user/withdraw`)
- `/api/v1` 같은 접두사는 필수가 아니다. (예: `/auth/kakao`)
- JSON 필드는 snake_case다. (Jackson `SNAKE_CASE` 전역 설정)
- 비즈니스 예외는 `BusinessException(ErrorCode)`로 던지고, `ErrorCode`에 HTTP 상태·코드·메시지를 정의한다.
  - 코드 접두사: 공통 `C`, 인증 `A`, 기록 `R`, 유저 `U`

## 인증
- 소셜 로그인(Google·카카오) 인가 코드는 백엔드가 처리한다.
- access token은 응답 body로, refresh token은 HttpOnly 쿠키로 전달한다.
- refresh token은 DB에 저장하고(컬럼명 `token`), 로그아웃은 refresh token 쿠키로 처리한다.
- OAuth state는 쿠키에 저장한다.

## 테스트
- 테스트는 `test` 프로필(`application-test.yml`)을 쓴다.
- 외부 API를 호출하는 테스트에는 `@Tag("external")`을 붙인다. CI에서는 제외된다.

## 코드 리뷰
@REVIEW.md
