package chapter3.item15;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Thing {

	/*
	 * item 15 클래스와 멤버의 접근권한을 최소화하라
	 * 
	 *  public 클래스의 인스턴스 필드는 되도록 public이 아니어야 한다.
	 *  예외 - 해당 클래스가 표현하는 추상 개념을 완성하는 데 꼭 필요한 
	 *        구성요소로써의 상수라면 public static final 필드로 공개해도 좋다
	 * 		  -> 반드시 기본 타입 값이나 불변 객체를 참조해야 한다
	 * */
	
	private static final Thing[] PRIVATE_VALUE = {};
	
	// 길이가 0이 아닌 배열은 모두 변경 가능하다
	// -> 클래스에서 public static final 배열 필드를 두거나 이 필드를 반환하는 접근자 메서드를 제공해서는 안된다.
	// 해경방안
	// case 1 - 앞 코드의 public 배열을 private으로 만들고 public 불변 리스트를 추가
	public static final List<Thing> VALUES = 
			Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUE));
	
	// case 2 - 배열ㅇ르 private으로 만들고 그 복사본을 반환하는 public 메서드를 추가하는 방법(방어적 복사)
	public static final Thing[] values() {
		return PRIVATE_VALUE.clone();
	}
}
