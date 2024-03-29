package chapter6.item39;

import java.util.ArrayList;
import java.util.List;

public class Sample2 {
	@ExceptionTest(ArithmeticException.class)
	public static void m1() { // 성공해야 한다.
		int i = 0;
		i = i / i;
	}
	
	@ExceptionTest(ArithmeticException.class)
	public static void m2() { // 실패해야한다. (다른 예외 발생)
		int[] a = new int[0];
		int i = a[1];
	}
	@ExceptionTest(ArithmeticException.class)
	public static void m3() {} // 실패해야 한다.(예외가 발생하지 않음)
	// 39 - 7
//	@ExceptionTest({ IndexOutOfBoundsException.class, 
//					 NullPointerException.class})
	// 39 - 9 반복 가능 애너테이션을 두번 단 코드 
	@ExceptionTest(IndexOutOfBoundsException.class)
	@ExceptionTest(NullPointerException.class)
	public static void doublyBad() {
		List<String> list = new ArrayList<>();
		// 자바 명세에 따르면 IndexOutOfBoundsException이나 NullPointerException을 던질 수 있다.
		list.addAll(5, null);
	}
}
