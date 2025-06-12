# CSH Utils Project Todo List

## 1. Banner 모듈 구현
- [x] 1.1 모듈 구조 설정
  - [x] 디렉토리 구조 생성
  - [x] pom.xml 설정
  - [x] 부모 pom.xml에 모듈 추가
- [x] 1.2 Core 패키지 구현
  - [x] BannerTheme enum 생성
  - [x] BannerConfig 클래스 생성
  - [x] AppBannerUtil 클래스 생성
- [x] 1.3 Model 패키지 구현
  - [x] BannerInfo 클래스 생성
- [x] 1.4 Renderer 패키지 구현
  - [x] BannerRenderer 인터페이스 생성
  - [x] ConsoleBannerRenderer 구현체 생성
- [x] 1.5 Template 패키지 구현
  - [x] BannerTemplate 인터페이스 생성
  - [x] DefaultBannerTemplate 구현체 생성
  - [x] CustomBannerTemplate 구현체 생성
- [x] 1.6 Detector 패키지 구현
  - [x] BannerDetector 인터페이스 생성
  - [x] DefaultBannerDetector 구현체 생성
  - [x] CustomBannerDetector 구현체 생성
- [x] 1.7 테스트 코드 수정
  - [x] BannerThemeTest 수정
    - [x] DEFAULT 테마의 ANSI 코드 처리 수정
  - [x] BannerInfoTest 수정
    - [x] 빌드 시간 비교 로직 수정
  - [x] ConsoleBannerRendererTest 수정
    - [x] 버전 정보 출력 검증 로직 수정

## 2. 코드 정리
- [x] 2.1 중복 코드 제거
  - [x] WelcomeUtil 클래스 제거
  - [x] Lombok 의존성 제거 및 수동 구현
- [ ] 2.2 패키지 구조 정리
  - [ ] io.csh.core 패키지 제거
  - [ ] io.csh.utils.core 패키지 정리
- [ ] 2.3 의존성 정리
  - [ ] 순환 참조 제거
  - [ ] 불필요한 의존성 제거

## 3. 문서화
- [x] 3.1 JavaDoc 작성
  - [x] 클래스 문서화
  - [x] 메서드 문서화
  - [x] 예제 코드 작성
- [ ] 3.2 README 업데이트
  - [ ] 프로젝트 구조 설명
  - [ ] 사용 방법 설명
  - [ ] 예제 코드 추가
- [ ] 3.3 CHANGELOG 작성
  - [ ] 버전별 변경사항 기록
  - [ ] 주요 기능 설명

## 4. 향후 개선사항
- [ ] 4.1 기능 개선
  - [ ] 배너 템플릿 시스템 개선
  - [ ] 테마 시스템 확장
  - [ ] 국제화 지원 강화
- [ ] 4.2 성능 개선
  - [ ] 렌더링 성능 최적화
  - [ ] 메모리 사용량 최적화
- [ ] 4.3 테스트 강화
  - [ ] 테스트 커버리지 향상
  - [ ] 성능 테스트 추가 