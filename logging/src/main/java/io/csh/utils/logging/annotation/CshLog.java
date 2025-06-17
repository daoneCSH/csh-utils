package io.csh.utils.logging.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 로거를 자동으로 생성하는 어노테이션
 * 클래스에 이 어노테이션을 사용하면 자동으로 로거가 생성됩니다.
 * 
 * 사용 예시:
 * <pre>
 * {@code
 * @cshLog
 * public class MyClass {
 *     // 자동으로 생성된 로거 사용
 *     public void someMethod() {
 *         log.info("Some message");
 *     }
 * }
 * }
 * </pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CshLog {
    /**
     * 로거 이름
     * 기본값은 클래스의 이름입니다.
     */
    String value() default "";
} 