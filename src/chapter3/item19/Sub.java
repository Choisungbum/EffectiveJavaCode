package chapter3.item19;

import java.time.Instant;

/*
 * item19 - 상속을 고려해 설계하고 문서화하라. 그러지 않았다면 상속을 금지하라
 * 
 * */

public class Sub extends Super{

	private final Instant instant;
	
	public Sub() {
		instant = Instant.now();
	}
	
	// 재정의 가능 메서드, 상위 클래스의 생성자가 호출한다
	@Override
	public void overrideMe() {
		System.out.println(instant);
	}
	
	public static void main(String[] args) {
		Sub sub = new Sub();
		sub.overrideMe();
	}
}
