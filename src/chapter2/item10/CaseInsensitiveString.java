package chapter2.item10;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// item 14 - Comparable을 구현할지 고민하라
// implements Comparable<CaseInsensitiveString> 
// -> CaseInsensitiveString 의 참조는 CaseInsensitiveString 참조와만 비교할 수 있다
public final class CaseInsensitiveString implements Comparable<CaseInsensitiveString>{ 
	private final String s;
	
	public CaseInsensitiveString(String s) {
		this.s = Objects.requireNonNull(s);
	}
	
	// case 1 - 대칭성위배 
//	@Override 
//	public boolean equals(Object o) {
//		// instanceof 연산자 객체가 어떤 클래스인지 어떤 클래스를 상속받았는지 확인하는데 사용하는 연산자
//		// 1이 2이거나 2를 상속받은 클래스라면 true, 그렇지 않으면 false
//		//  1						2
//		if (o instanceof CaseInsensitiveString) {
//			// equalsIgnoreCase : 문자열이 대문자 소문자 구분없이 같은 문자열인지 확인
//			return s.equalsIgnoreCase(
//					((CaseInsensitiveString) o).s);
//		}
//		if (o instanceof String) {
//			return s.equalsIgnoreCase((String) o);
//		}
//		return false;
//	}
	
	// case 3 : 정상동작 
	@Override 
	public boolean equals(Object o) {
		return o instanceof CaseInsensitiveString &&
			   ((CaseInsensitiveString) o).s.equalsIgnoreCase(s);
	}
	
	public static void main(String[] args) {
		
		// case 1 : CaseInsensitiveString 는 String의 존재를 알지만, String는 CaseInsensitiveString의 존재를 모르기 때문에
		// 			담은과 같은결과가 나온다 -> 대칭성 위반
		CaseInsensitiveString cls = new CaseInsensitiveString("polish");
		String s = "polish";
		
		System.out.println("cls.equals(s) :" + cls.equals(s)); // true
		System.out.println("s.equals(cls) :" + s.equals(cls)); // false
		
		// case 2 : false 를 반환하기는 하지만 구현하기 나름이라 jdk의 버전이 달라지거나 다른 jdk에서는 
		//			true를 반환하거나 런타임 오류를 던질 수도 있다.  -> equals 규약을 어기면 그 객체를 사용하는 다른 개체들이 어떻게 반응할지 알 수 없다
		List<CaseInsensitiveString> list = new ArrayList<>();
		list.add(cls);
		System.out.println("list.contains(s) :" + list.contains(s)); // false
		
	}

	/*
	 * item 14
	 * Comparable을 구현할지 고민하라
	 * 
	 * */
	// 객체 참조 필드가 하나뿐인 비교자 
	@Override
	public int compareTo(CaseInsensitiveString cls) {
		return String.CASE_INSENSITIVE_ORDER.compare(s, cls.s);
	}
}
