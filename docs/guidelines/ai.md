# AI 작업 가이드라인

## 중요: 이 문서는 AI가 항상 가장 먼저 확인해야 하는 문서입니다.

## 문서 구조 및 역할

### 1. 프로젝트 루트 문서
- **README.md**
  - 프로젝트 개요
  - 주요 기능
  - 사용 방법
  - 의존성 정보

- **CHANGELOG.md**
  - 버전 히스토리
  - 변경사항 기록

- **CONTRIBUTING.md**
  - 기여 가이드
  - 문서 구조
  - 작업 절차

- **pom.xml**
  - 프로젝트 POM
  - 버전 관리
  - 의존성 관리

### 2. docs/ 디렉토리 구조
```
docs/
├── guides/           # 사용자 가이드
│   └── usage.md      # 사용 방법 가이드
├── design/          # 설계 문서
│   └── overview.md   # 전체 설계 개요
├── releases/        # 릴리스 노트
│   └── [version files]
└── guidelines/     # 가이드라인
    └── ai.md       # AI 관련 가이드라인
```

### 3. 디렉토리별 역할

#### guides/
- 사용 가이드 문서
- API 사용 예제
- 설정 및 구성 가이드

#### design/
- 설계 문서
- 아키텍처 문서
- 시스템 구조 설명

#### releases/
- 릴리즈 문서
- 버전별 변경사항 상세 기록

#### guidelines/
- 프로젝트 가이드라인
- 작업 규칙
- 문서 작성 규칙

## AI 작업 순서

1. 이 문서(ai.md) 확인
   - 문서 구조 파악
   - 작업 순서 확인

2. CONTRIBUTING.md 확인
   - 기여 가이드 확인
   - 작업 절차 확인

3. docs/releases/ 확인
   - 최신 릴리즈 문서 확인
   - 변경사항 문서화 필요 여부 확인

4. CHANGELOG.md 확인
   - 버전 히스토리 확인
   - 릴리즈 문서 작성 시 참조

5. 작업 수행
   - 변경사항 문서화
   - 버전 업데이트
   - 테스트 수행

## 문서 작성 규칙

### 1. 문서 위치 결정
- 사용자 가이드: `docs/guides/`
- 설계 문서: `docs/design/`
- 릴리즈 노트: `docs/releases/`
- 가이드라인: `docs/guidelines/`

### 2. 파일명 규칙
- 사용자 가이드: `usage.md`
- 설계 문서: `overview.md`
- 릴리즈 노트: `{버전}.md`
- 가이드라인: `{주제}.md`

### 3. 문서 검증 체크리스트
- [ ] 적절한 디렉토리에 작성되었는가?
- [ ] 파일명이 규칙을 따르는가?
- [ ] 중복되는 내용이 없는가?
- [ ] 관련 문서와의 링크가 올바른가?
- [ ] 문서 형식이 일관성 있는가?
- [ ] 필요한 모든 섹션이 포함되어 있는가?

## 주의사항

1. 새로운 문서 생성 전
   - 기존 문서 구조 확인
   - 중복 내용 확인
   - 적절한 위치 확인
   - 파일명 규칙 준수

2. 문서 수정 시
   - 관련 문서 모두 확인
   - 링크 업데이트 확인
   - 버전 정보 업데이트
   - 문서 구조 준수

3. 릴리즈 문서 작성 시
   - CHANGELOG.md 업데이트
   - docs/releases/에 새 문서 생성
   - 버전 정보 일관성 확인
   - pom.xml의 버전 정보 업데이트

4. 버전 업데이트 시 필수 작업
   - pom.xml 버전 업데이트
   - 빌드 실행하여 버전 동기화 확인
   - 테스트 실행하여 정상 동작 확인
   - 변경사항 문서화 (CHANGELOG.md, 릴리즈 노트)
   - 모든 문서의 버전 참조 업데이트

## 코드 작성 규칙

### 1. 클래스 구조
- 패키지 구조 준수
- 적절한 접근 제어자 사용
- 명확한 클래스/메서드 이름
- 일관된 코드 스타일
- **Lombok 등 코드 생성 라이브러리 사용 금지**

### 2. 문서화
- 모든 public API에 JavaDoc 주석 필수
- 모든 public 클래스, 인터페이스, 메서드에 JavaDoc 주석 필수
- 모든 public 필드에 JavaDoc 주석 필수
- 모든 생성자에 JavaDoc 주석 필수
- 모든 enum 상수에 JavaDoc 주석 필수
- 클래스 레벨 문서화 필수
- 메서드 파라미터와 반환값 문서화 필수
- 예외 처리 문서화 필수
- JavaDoc 주석 누락 시 빌드 실패

### 3. 테스트
- 단위 테스트 작성
- 테스트 커버리지 확인
- 테스트 케이스 문서화
- 테스트 실행 결과 검증

### 4. 의존성 관리
- 최소한의 의존성만 사용
- 버전 충돌 방지
- 의존성 범위 적절히 설정

## 작업 검증 체크리스트

### 1. 코드 검증
- [ ] 컴파일 에러 없음
- [ ] JavaDoc 주석 누락 없음
- [ ] 테스트 통과
- [ ] 코드 스타일 준수
- [ ] 문서화 완료

### 2. 문서 검증
- [ ] 관련 문서 업데이트
- [ ] 버전 정보 일관성
- [ ] 링크 정상 작동
- [ ] 문서 형식 준수

### 3. 빌드 검증
- [ ] 전체 빌드 성공
- [ ] JavaDoc 생성 성공
- [ ] 의존성 해결
- [ ] 아티팩트 생성
- [ ] 배포 준비 완료
- [ ] 빌드 로그 파일 생성 및 검증

### 4. 로그 파일 관리
- [ ] 모든 빌드 작업은 반드시 로그 파일 생성
  - 빌드 로그: `build.log`
  - 테스트 로그: `test.log`
- [ ] 로그 파일 생성 명령어 예시:
  ```bash
  # 빌드 로그
  mvn clean install -f pom.xml > build.log 2>&1
  
  # 테스트 로그
  mvn test -Dtest=TestClassName > test.log 2>&1
  ```
- [ ] 로그 파일 검증 체크리스트
  - [ ] 로그 파일이 정상적으로 생성되었는가?
  - [ ] 표준 출력과 에러 출력이 모두 캡처되었는가?
  - [ ] 로그 파일에 빌드/테스트 결과가 명확히 기록되었는가?
  - [ ] 에러가 발생한 경우 상세 정보가 기록되었는가?

### 5. 통합 검증
- [ ] 성능 영향 없음
- [ ] 보안 취약점 없음
- [ ] 사용자 영향 최소화

## 프로젝트 특성 및 제약사항

### 1. Java Agent 호환성 필수
- 이 프로젝트는 Java Agent에서 사용될 수 있어야 함
- 일반 Java 프로젝트에서도 사용 가능해야 함
- 모든 개발 및 변경사항은 Java Agent 환경을 고려해야 함
- 일반적인 Java 라이브러리와는 다른 제약사항이 존재

### 2. 필수 고려사항
- 클래스로더 분리 환경에서 동작 가능해야 함
- 메인 애플리케이션과 독립적인 실행 지원
- 최소한의 의존성만 사용
- 가벼운 구현 필요

### 3. 금지사항
- 무거운 프레임워크 사용 금지 (Spring, Log4j2 등)
- 메인 애플리케이션의 클래스패스에 의존하는 기능 사용 금지
- Lombok 등 코드 생성 라이브러리 사용 금지

### 4. 권장사항
- 순수 Java 기능 우선 사용
- 가벼운 로깅 구현 직접 개발
- 독립적인 설정 관리
- 최소한의 외부 의존성

### 5. 설정값 우선순위
모든 설정값은 다음 우선순위를 따라야 합니다:
1. 시스템 프로퍼티 (-D 옵션으로 설정)
2. 어플리케이션 프로퍼티 (application.properties/yml)
3. 기본값 (코드에 하드코딩된 값)

예시:
```java
// 시스템 프로퍼티 확인
String value = System.getProperty("my.property");

// 어플리케이션 프로퍼티 확인
if (value == null) {
    value = applicationProperties.getProperty("my.property");
}

// 기본값 사용
if (value == null) {
    value = "default";
}
```

- 모든 설정값은 반드시 이 우선순위를 준수해야 함
- 설정값 변경 시 우선순위를 고려하여 구현해야 함
- 문서화 시 우선순위를 명시해야 함 