package chapter6.item39;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/*
 * 명시한 예외를 던져야만 성공하는 테스트 메서등요 애너테이션 
 * */
@Retention(RUNTIME)
@Target(METHOD)
//39 - 8 반복 가능한 애너테이션 타입 
	// 반복 가능한 애터테이션 
@Repeatable(ExceptionTestContainer.class)
public @interface ExceptionTest {
//	Class<? extends Throwable> value();
	// 39 - 6
	Class<? extends Throwable>[] value();
	
}
