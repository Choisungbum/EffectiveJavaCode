package chapter6.item39;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/*
 * 명시한 예외를 던져야만 성공하는 테스트 메서등요 애너테이션 
 * */
@Retention(RUNTIME)
@Target(METHOD)
public @interface ExceptionTest {
	Class<? extends Throwable> value();
}
