package chapter6.item39;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
//39 - 8 반복 가능한 애너테이션 타입 
	// 반복 가능한 애터테이션 
public @interface ExceptionTestContainer {
	ExceptionTest[] values();
}
