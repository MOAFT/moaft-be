# MOAFT Backend

## 개발 환경

- Java 21
- Spring Boot 4.1.2
- Gradle (Groovy)
- MySQL

## 로컬 실행 방법

### 1. MySQL 설정

로컬 MySQL에 `moaft` 데이터베이스를 생성하고 접속 계정을 설정합니다.

```sql
CREATE DATABASE moaft
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;
```

다음 환경변수를 설정합니다.

| 환경변수 | 설명 |
|---|---|
| `DB_URL` | MySQL JDBC URL |
| `DB_USERNAME` | MySQL 사용자 이름 |
| `DB_PASSWORD` | MySQL 비밀번호 |

환경변수 설정 예시 (macOS/Linux):

```bash
export DB_URL="jdbc:mysql://localhost:3306/moaft"
export DB_USERNAME="moaft_dev"
export DB_PASSWORD="본인_비밀번호"
```

실제 비밀번호는 Git에 커밋하지 않습니다.

### 2. 프로젝트 실행

리포지토리를 Clone하고 JDK 21을 설정한 후, 다음 명령어로 실행합니다.

```bash
./gradlew bootRun --args='--spring.profiles.active=local'
```

## 빌드 및 테스트

MySQL 서버가 실행 중이고 환경변수가 설정된 상태에서 다음 명령어를 실행합니다.

```bash
SPRING_PROFILES_ACTIVE=local ./gradlew clean build
```

현재 DB 연결 테스트는 로컬 MySQL 환경이 필요합니다.