package chapter6.item39;

public class Sample {
	@Test public static void m1() {} 	// 성공해야한다
	public static void m2() {}
	@Test public static void m3() { 	// 실패해야 한다
		throw new RuntimeException("실패");
	}
	public static void m4() {}
	@Test public void m5() {} 			// 잘못사용한 예 : 정적 메서득가 아니다
	public static void m6() {}
	@Test static void m7() { 			// 실패해야 한다
		throw new RuntimeException("실패");
	}
	public static void m8() {}
}
