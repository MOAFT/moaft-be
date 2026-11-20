## Backend

### 개발 환경

- Java 21
- Spring Boot 4.1.2
- Gradle (Groovy)
- MySQL

### 프로젝트 실행

1. 리포지토리를 Clone합니다.
2. JDK 21을 설정합니다.
3. 로컬 프로필로 애플리케이션을 실행합니다.

```bash
./gradlew bootRun --args='--spring.profiles.active=local'
```

현재는 DB 연동 전 단계이므로 로컬 프로필에서 DB 자동 설정을 임시로 비활성화한 상태입니다.

### 빌드

```bash
./gradlew clean build
```

### 환경변수

DB 연동에 필요한 환경변수는 `.env.example`을 참고합니다.

- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`

실제 환경변수 값은 Git에 커밋하지 않습니다.