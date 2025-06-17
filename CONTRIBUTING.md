# CSH Utils Contributing Guide

## 기여자 행동 강령
- 존중과 배려
- 전문적인 의사소통
- 건설적인 피드백

## 개발 환경 설정
1. 저장소 클론
   ```bash
   git clone https://github.com/your-username/csh-utils.git
   cd csh-utils
   ```

2. 의존성 설치
   ```bash
   mvn clean install
   ```

3. IDE 설정
   - IntelliJ IDEA 또는 Eclipse 사용 권장
   - Java 17 SDK 설정
   - Maven 프로젝트로 import

4. 테스트 실행
   ```bash
   mvn test
   ```

## 작업 절차

1. 이슈 생성
   - 버그 리포트
   - 기능 요청
   - 문서 개선

2. 브랜치 생성
   ```bash
   git checkout -b feature/your-feature-name
   # 또는
   git checkout -b fix/your-bug-fix
   ```

3. 코드 작성
   - 코딩 컨벤션 준수
   - 테스트 코드 작성
   - 문서화

4. 테스트 작성
   - 단위 테스트
   - 통합 테스트
   - 성능 테스트

5. PR 생성
   - PR 템플릿 작성
   - 변경사항 설명
   - 관련 이슈 링크

## 코드 스타일

### Java 코딩 컨벤션
- Google Java Style Guide 준수
- 들여쓰기: 4 spaces
- 최대 줄 길이: 120자
- 클래스/메서드 주석 필수

### 테스트 작성 규칙
- 테스트 클래스명: `{테스트대상클래스}Test`
- 테스트 메서드명: `test{테스트케이스설명}`
- 각 테스트는 독립적으로 실행 가능해야 함
- 테스트 커버리지 80% 이상 유지

### 문서화 규칙
- JavaDoc 주석 필수
- README.md 업데이트
- CHANGELOG.md 업데이트
- API 문서 업데이트

## PR 프로세스

1. PR 템플릿 작성
   - 변경사항 설명
   - 테스트 결과
   - 관련 이슈

2. 코드 리뷰
   - 코드 품질 검토
   - 테스트 커버리지 확인
   - 문서화 검토

3. CI/CD 통과
   - 빌드 성공
   - 테스트 통과
   - 코드 품질 검사 통과

4. 승인 및 머지
   - 최소 1명의 승인 필요
   - 충돌 해결
   - 머지 후 이슈 닫기

## 버전 관리

### 시맨틱 버저닝
- 주 버전: 호환되지 않는 API 변경
- 부 버전: 이전 버전과 호환되는 기능 추가
- 수 버전: 버그 수정

### 릴리즈 프로세스
1. 버전 업데이트
   - pom.xml 버전 수정
   - CHANGELOG.md 업데이트
   - 태그 생성

2. 문서화
   - 릴리즈 노트 작성
   - API 문서 업데이트
   - README.md 업데이트

3. 배포
   - Maven Central 배포
   - GitHub 릴리즈 생성
   - 변경사항 공지

## 문제 해결

### 빌드 실패
- Maven 캐시 삭제
- 의존성 확인
- Java 버전 확인

### 테스트 실패
- 로컬 환경 확인
- 테스트 데이터 확인
- 의존성 버전 확인

### 문서화 문제
- 마크다운 문법 확인
- 링크 유효성 확인
- 이미지 경로 확인 