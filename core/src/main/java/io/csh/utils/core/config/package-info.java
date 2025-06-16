/**
 * 설정 관리 관련 클래스들을 포함하는 패키지
 * 
 * 주요 클래스:
 * - SystemProperties: 시스템 전반의 프로퍼티를 관리하는 유틸리티 클래스
 *   - 체인 패턴을 사용하여 다양한 소스(시스템 프로퍼티, application.properties, JAR 파일명)에서 값을 조회
 *   - 모든 모듈에서 공통으로 사용 가능한 범용 프로퍼티 관리
 *   - 프로퍼티 값의 타입 변환(String, Boolean, Integer, Long) 지원
 * 
 * - PropertyManager: 로깅 시스템의 설정을 관리하는 클래스
 *   - 로그 레벨, 포맷, 파일 경로 등 로깅 관련 설정 담당
 *   - LoggingProperties를 통한 로깅 시스템 제어
 *   - 로깅 모듈에 특화된 설정 관리
 *   - 프로퍼티 캐시 기능 제공
 */
package io.csh.utils.core.config; 